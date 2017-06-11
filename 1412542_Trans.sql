-- Hủy đăng ký đề cá nhân
alter proc huyDangKyDeCaNhan
	@mssv char(5), 
	@made char(5), 
	@mamon char(6), 
	@ketqua bit out
as
begin
	begin tran huyDangKyDeCaNhan
	begin try 
	--	set tran isolation level repeatable read
	if not exists(select*from DangKyDeCaNhan where MSSV = @mssv and MaDe = @made and MaMH = @mamon and TrangThai= 1)
	begin
		set @ketqua = 0;
		raiserror(N'Sinh viên chưa đăng ký đề môn này', 16, 1);
		commit tran huyDangKyDeCaNhan;  
		return;
	end
	waitfor delay '00:00:10'
	update DangKyDeCaNhan set TrangThai = 0 where MSSV = @mssv and MaDe = @made and MaMH = @mamon and TrangThai= 1
	end try  
	begin catch 
		declare @ErrorMessage NVARCHAR(4000), @ErrorSeverity INT, @ErrorState INT;  
		select   @ErrorMessage = ERROR_MESSAGE(),  @ErrorSeverity = ERROR_SEVERITY(),  @ErrorState = ERROR_STATE(); 
		raiserror (@ErrorMessage, @ErrorSeverity, @ErrorState );  
		rollback tran huyDangKyDeCaNhan;
		set @ketqua = 0;
		return;
	end catch; 
	set @ketqua = 1;
	commit tran huyDangKyDeCaNhan;  
end

declare @kq bit
execute huyDangKyDeCaNhan '11001', 'De001', 'CTT001', @kq out

select*from DangKyDeCaNhan where MSSV = '11001'
update DangKyDeCaNhan set TrangThai = 1 where MSSV = '11001' and MaDe = 'De001' and MaMH = 'CTT001'

----
-- HỦY ĐĂNG KÝ ĐỀ NHÓM
create proc huyDangKyDeNhom(@manhom char(4), @made char(5), @mamon char(6), @ketqua bit out)
as
begin
	begin tran huyDangKyDeNhom
	begin try 
	--set isolation repeatable read
	if not exists(select*from DangKyDeNhom where MaNhom = @manhom and MaDe = @made and MaMH = @mamon and TrangThai= 1)
	begin
		set @ketqua = 0;
		raiserror(N'Sinh viên chưa đăng ký đề môn này', 16, 1);
		commit tran huyDangKyDeCaNhan;  
		return;
	end
	waitfor delay '00:00:10'
	update DangKyDeNhom set TrangThai = 0 where MaNhom = @manhom and MaDe = @made and MaMH = @mamon and TrangThai= 1
	end try  
	begin catch 
		declare @ErrorMessage NVARCHAR(4000);  
		declare @ErrorSeverity INT;  
		declare @ErrorState INT;  
		select   @ErrorMessage = ERROR_MESSAGE(),  @ErrorSeverity = ERROR_SEVERITY(),  @ErrorState = ERROR_STATE(); 
		raiserror (@ErrorMessage, @ErrorSeverity, @ErrorState );  
		rollback tran huyDangKyDeNhom;
		set @ketqua = 0;
		return;
	end catch; 
	set @ketqua = 1;
	commit tran huyDangKyDeNhom;  
end
----------------------------------------------------------------------------------

--Xóa đề của môn
alter PROCEDURE XoaDeMon 
	@maMon char(6), 
	@maDe char(5), 
	@kq bit out
AS
		BEGIN TRANSACTION XoaDeMon
		BEGIN TRY
			if 1 = (select LoaiDoAn from De_Mon WHERE MaMH = @maMon AND MaDe = @maDe and TinhTrang = 1)
				update DangKyDeCaNhan set TrangThai = 0 where MaMH = @maMon AND MaDe = @maDe
			else 
				update DangKyDeNhom set TrangThai = 0 where MaMH = @maMon AND MaDe = @maDe
			UPDATE De_Mon SET TinhTrang = 0 WHERE MaMH = @maMon AND MaDe = @maDe
		END TRY
		BEGIN CATCH
			DECLARE @ErrorMes nvarchar(4000);
			DECLARE @ErrorSev int;
			DECLARE @ErrorState int;

			SELECT @ErrorMes = ERROR_MESSAGE(), @ErrorSev = ERROR_SEVERITY(), @ErrorState = ERROR_STATE()

			SET @kq = 0
			RAISERROR(@ErrorMes, @ErrorSev, @ErrorState)
			ROLLBACK TRAN XoaDeMon
			RETURN
		END CATCH
		SET @kq = 1
		COMMIT TRANSACTION XoaDeMon
/*
declare @kq bit
execute XoaDeMon 'CTT001', 'De001', @kq out

select* from De_Mon where MaDe = 'De001'
UPDATE De_Mon SET TinhTrang = 1 WHERE MaMH = 'CTT001' AND MaDe = 'De001'
update DangKyDeCaNhan set TrangThai = 1 where MaMH = 'CTT001' AND MaDe = 'De001'*/
---------------------------------------------------------------------

-- THÊM ĐỀ
alter procedure ThemDe_wait
	@moTa nvarchar(300),
	@kq bit out
as
begin
	begin transaction ThemDe_wait
	--set tran isolation level repeatable read
		begin try
			declare @dem int, @maDe char(5)
			set @dem = (select count(*) from De)
			if (@dem < 10)
				set @maDe = CONCAT('De00',@dem)
			else if (@dem < 100)
				set @maDe = CONCAT('De0', @dem)
			else 
				set @maDe = CONCAT('De',@dem)
			waitfor delay '00:00:10'
			insert into De values (@maDe, @moTa)
		end try
		begin catch
			declare @ErrorMessage NVARCHAR(4000), @ErrorSeverity INT, @ErrorState INT;  
			select   @ErrorMessage = ERROR_MESSAGE(),  @ErrorSeverity = ERROR_SEVERITY(),  @ErrorState = ERROR_STATE(); 
			raiserror (@ErrorMessage, @ErrorSeverity, @ErrorState );  
			rollback tran ThemDe_wait;
			set @kq = 0;
			return;
		end catch
	set @kq = 1
	commit tran ThemDe_wait
end

/*
declare @kq bit
exec ThemDe_wait 'no decription 1', @kq out

delete from De where Mota = 'no decription 1'*/

-----------------------------------------------------------------------------

-- Phân công quản lý nhóm
CREATE PROCEDURE PhanCongQuanLy @nhom char(4), @maDe char(5), @maMon char(6), @kq bit out
AS
	BEGIN TRANSACTION
		BEGIN TRY
			DECLARE @gv char(5)
			SET @gv = (SELECT TOP 1 MaGV
					   FROM QuanLiNhom
					   WHERE MaDe = @maDe AND MaMH = @maMon AND TinhTrang = 1
					   GROUP BY MaGV
					   HAVING COUNT(MaNhom) <= ALL (SELECT COUNT(MaNhom)
													 FROM QuanLiNhom Q1
													 WHERE Q1.MaDe = @maDe AND Q1.MaMH= @maMon AND TinhTrang = 1
													 Group by MaGV))
			INSERT INTO QuanLiNhom VALUES(@gv, @nhom, @maDe, @maMon, 1)
		END TRY
		BEGIN CATCH
			DECLARE @ErrorMes nvarchar(4000);
			DECLARE @ErrorSev int;
			DECLARE @ErrorState int;

			SELECT @ErrorMes = ERROR_MESSAGE(), @ErrorSev = ERROR_SEVERITY(), @ErrorState = ERROR_STATE()

			SET @kq = 0
			RAISERROR(@ErrorMes, @ErrorSev, @ErrorState)
			ROLLBACK TRAN
			RETURN
		END CATCH
		SET @kq = 1
		COMMIT TRANSACTION

-- ĐĂNG KÝ ĐỀ CÁ NHÂN
alter PROCEDURE usp_DangKyDeCaNhan @mssv char(5), @maDe char(5), @maMon char(6), @kq bit out
AS
BEGIN TRANSACTION
	BEGIN TRY
		begin
			declare @sldk tinyint, @sldkToiDa tinyint
			set @sldk = (select SLDangKy from De_Mon where MaDe = @maDe AND MaMH = @maMon and TinhTrang = 1)
			set @sldkToiDa = (select SLDangKyToiDa from De_Mon where MaDe = @maDe AND MaMH = @maMon and TinhTrang = 1)
			if (@sldk < @sldkToiDa)
			begin
				INSERT INTO DangKyDeCaNhan VALUES(@maDe, @maMon, @mssv, 1)
				update De_Mon set SLDangKy = @sldk + 1 where MaDe = @maDe AND MaMH = @maMon and TinhTrang = 1
			end
			else 
			begin
				set @kq = 0;
				raiserror(N'Đề đã đủ số lượng đăng ký', 16, 1);
				commit tran DangKyDe;  
				return;
			end
		end
	END TRY
	BEGIN CATCH
		DECLARE @ErrorMes nvarchar(4000), @ErrorSev int, @ErrorState int;
		SELECT @ErrorMes = ERROR_MESSAGE(), @ErrorSev = ERROR_SEVERITY(), @ErrorState = ERROR_STATE()

		SET @kq = 0
		RAISERROR(@ErrorMes, @ErrorSev, @ErrorState)
		ROLLBACK TRAN
		RETURN
	END CATCH
	SET @kq = 1
COMMIT TRANSACTION

/*
declare @kq bit
exec usp_DangKyDeCaNhan '11001', 'De1', 'CTT001', @kq out
----
delete from DangKyDeCaNhan where MaDe = 'De1' 
update De_Mon set SLDangKy = 0 where MaDe = 'De1'
select * from De_Mon where MaDe = 'De1'
*/
-----------

-- ĐĂNG KÝ ĐỀ NHÓM
alter PROCEDURE usp_DangKyDeNhom @maNhom char(4), @maDe char(5), @maMon char(6), @kq bit out
AS
BEGIN TRANSACTION
	BEGIN TRY
		begin
			declare @sldk tinyint, @sldkToiDa tinyint
			set @sldk = (select SLDangKy from De_Mon where MaDe = @maDe AND MaMH = @maMon and TinhTrang = 1)
			set @sldkToiDa = (select SLDangKyToiDa from De_Mon where MaDe = @maDe AND MaMH = @maMon and TinhTrang = 1)
			if ((@sldk + 1) < @sldkToiDa)
			begin
				INSERT INTO DangKyDeNhom VALUES(@maDe, @maMon, @maNhom, 1)
				update De_Mon set SLDangKy = @sldk + 1 where MaDe = @maDe AND MaMH = @maMon and TinhTrang = 1
				declare @kqua bit
				exec PhanCongQuanLy @maNhom, @maDe, @maMon, @kqua out
			end
			else 
			begin
				set @kq = 0;
				raiserror(N'Đề đã đủ số lượng đăng ký', 16, 1);
				commit tran DangKyDe;  
				return;
			end
		end
	END TRY
	BEGIN CATCH
		DECLARE @ErrorMes nvarchar(4000), @ErrorSev int, @ErrorState int;
		SELECT @ErrorMes = ERROR_MESSAGE(), @ErrorSev = ERROR_SEVERITY(), @ErrorState = ERROR_STATE()

		SET @kq = 0
		RAISERROR(@ErrorMes, @ErrorSev, @ErrorState)
		ROLLBACK TRAN
		RETURN
	END CATCH
	SET @kq = 1
COMMIT TRANSACTION

------------------------------------------------------------------

--Sửa thông tin đề của môn
alter PROCEDURE SuaDeMon
	@maMon char(6), 
	@maDe char(5), 
	@slDKToiDa tinyint, 
	@slsvNhom tinyint, 
	@ngayBD datetime, 
	@kq bit out 
AS
	BEGIN TRANSACTION
			set tran isolation level read uncommited
		--	set tran isolation level read uncommited
		UPDATE De_Mon 
		SET SLDangKyToiDa = @slDKToiDa, SLSVNhom = @slsvNhom, NgayBDDangKy = @ngayBD 
		WHERE MaMH = @maMon AND MaDe = @maDe and TinhTrang = 1
		waitfor delay '00:00:10'
		declare @slsv int, @slsvthem int
		set @slsvthem = (select SLSVNhom from inserted)
		if (@slsvthem < 1) 
		begin
			raiserror(N'Số lượng sinh viên của nhóm phải lớn hơn 0', 16, 1)
			rollback tran
			SET @kq = 0
			return
		end
		set @slsv = (select max(SLSVNhom) from De_Mon )
		begin
			raiserror(N'Không thể chỉnh sửa số lượng sinh viên/nhóm vì đã có nhóm đăng kí có số lượng thành viên vượt quá số lượng này', 16, 1)
			rollback tran
			SET @kq = 0
			return
		end
		SET @kq = 1
	COMMIT TRANSACTION

/*
declare @kq bit 
exec SuaDeMon_wait 'CTT001', 'De1', 1, 1, '05/01/2017', @kq out -- Sửa ngày bắt đầu đăng ký

select * from De_Mon where MaDe = 'De1'*/
------------------------------------------------------------------------

-- ĐỌC THÔNG TIN ĐỀ MÔN của giáo viên
create proc DS_DeMon 
	@maGV char(5)
as
begin
	begin tran
		begin try
			select d.MaDe, d.MaMH, d.LoaiDe,d.LoaiDoAn, d.NgayBDDangKy,d.SLDangKyToiDa, d.SLDangKy, d.SLSVNhom, d.SLSVNhom, pt.Deadline
			from De_Mon d, PhuTrachDe pt 
			where d.MaDe = pt.MaDe and d.MaMH = pt.MaMH and pt.MaGV = @maGV and pt.TinhTrang = 1
		end try
		begin catch
			DECLARE @ErrorMes nvarchar(4000), @ErrorSev int, @ErrorState int;
			SELECT @ErrorMes = ERROR_MESSAGE(), @ErrorSev = ERROR_SEVERITY(), @ErrorState = ERROR_STATE()

			RAISERROR(@ErrorMes, @ErrorSev, @ErrorState)
			ROLLBACK TRAN
			RETURN
		end catch
	commit tran
end

