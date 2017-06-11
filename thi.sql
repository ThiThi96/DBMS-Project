--UR
GO
CREATE PROCEDURE DoiMatKhau @manguoidung varchar(5), @matkhaucu nvarchar(20), @matkhaumoi nvarchar(20), @ketqua int out
AS
BEGIN
	BEGIN TRANSACTION
		BEGIN TRY  
		IF NOT EXISTS (SELECT*FROM Users WHERE MaNguoiDung = @manguoidung AND MatKhau = @matkhaucu AND TinhTrang='1')
			BEGIN
				SET @ketqua = '0'
				RAISERROR(N'Mật khẩu sai!!!', 16, 1)
				RETURN
			END
			WAITFOR DELAY '00:00:10'
			UPDATE Users SET MatKhau = @matkhaumoi WHERE MaNguoiDung = @manguoidung AND MatKhau = @matkhaucu AND TinhTrang='1'
		END TRY  
	BEGIN CATCH 
		DECLARE @ErrorMessage NVARCHAR(4000)  
		DECLARE @ErrorSeverity INT  
		DECLARE @ErrorState INT  
		SELECT   
			@ErrorMessage = ERROR_MESSAGE(),  
			@ErrorSeverity = ERROR_SEVERITY(),  
			@ErrorState = ERROR_STATE() 
		RAISERROR (@ErrorMessage, @ErrorSeverity, @ErrorState )  
		ROLLBACK TRANSACTION
		SET @ketqua = '0'
		RETURN
	END CATCH 
	SET @ketqua = '1'
	commit TRANSACTION  
END

GO
CREATE PROCEDURE KhoaTaiKhoan
@MaNguoiDung char(5),
@kq bit out
AS
BEGIN
	BEGIN TRAN
		BEGIN TRY
			UPDATE Users
			SET TinhTrang = 0
			WHERE MaNguoiDung = @MaNguoiDung
		END TRY
		BEGIN CATCH
			DECLARE @ErrorMes nvarchar(4000)
			DECLARE @ErrorSev int
			DECLARE @ErrorState int

			SELECT @ErrorMes = ERROR_MESSAGE(), @ErrorSev = ERROR_SEVERITY(), @ErrorState = ERROR_STATE()
			RAISERROR(@ErrorMes, @ErrorSev, @ErrorState)
			ROLLBACK TRAN
			SET @kq = 0
			RETURN
		END CATCH
		SET @kq = 1
	COMMIT TRAN
END

--Phantom
GO
CREATE PROCEDURE PhanCongQuanLy @nhom char(4), @maDe char(5), @maMon char(6), @del int, @kq bit out
AS
	BEGIN TRANSACTION
		BEGIN TRY
			DECLARE @gv char(5)
			SELECT COUNT(MaNhom) SL
							INTO tempTable
							FROM QuanLiNhom Q1
							WHERE Q1.MaDe = @maDe AND Q1. MaMH = @maMon AND TinhTrang = '1'
							GROUP BY MaGV
			IF (@del = 1)
				WAITFOR DELAY '00:00:10'
			SET @gv = (SELECT TOP 1 MaGV
					   FROM QuanLiNhom
					   WHERE MaDe = @maDe AND MaMH = @maMon AND TinhTrang = '1'
					   GROUP BY MaGV
					   HAVING COUNT(MaNhom) <= ALL (SELECT SL
													FROM tempTable))
			DROP TABLE tempTable
			INSERT INTO QuanLiNhom VALUES(@gv, @nhom, @maDe, @maMon, '1')
		END TRY
		BEGIN CATCH
			DECLARE @ErrorMes nvarchar(4000)
			DECLARE @ErrorSev int
			DECLARE @ErrorState int

			SELECT @ErrorMes = ERROR_MESSAGE(), @ErrorSev = ERROR_SEVERITY(), @ErrorState = ERROR_STATE()

			SET @kq = 0
			RAISERROR(@ErrorMes, @ErrorSev, @ErrorState)
			ROLLBACK TRAN
			RETURN
		END CATCH
		SET @kq = 1
		COMMIT TRANSACTION							 

--Lost Update
GO
CREATE PROCEDURE suaThongTinDe @made char(5), @mota nvarchar(200),  @del int, @ketqua bit out
AS
BEGIN
	BEGIN TRANSACTION
		SET TRANSACTION ISOLATION LEVEL READ COMMITTED 
		BEGIN TRY
			IF NOT EXISTS (SELECT*FROM De WHERE MaDe = @made)
				BEGIN
					RAISERROR(N'Mã đề không tồn tại!!', 16, 1)
					SET @ketqua = '0'
					ROLLBACK TRANSACTION
					RETURN
				END
			IF EXISTS (SELECT*FROM De_Mon WHERE MaDe = @made)
				BEGIN
					RAISERROR(N'Không thể sửa đề vì đã có lớp đăng ký đề này!!', 16, 1)
					SET @ketqua = '0'
					ROLLBACK TRANSACTION
					RETURN
				END
		
				UPDATE De SET Mota = @mota WHERE MaDe = @made
				IF (@del = 1)
					WAITFOR DELAY '00:00:05'
		END TRY
		BEGIN CATCH 
			DECLARE @ErrorMessage NVARCHAR(4000)  
			DECLARE @ErrorSeverity INT  
			DECLARE @ErrorState INT  
			SELECT   
				@ErrorMessage = ERROR_MESSAGE(),  
				@ErrorSeverity = ERROR_SEVERITY(),  
				@ErrorState = ERROR_STATE() 
			RAISERROR (@ErrorMessage, @ErrorSeverity, @ErrorState )  
			ROLLBACK TRANSACTION
			SET @ketqua = '0'
			RETURN
		END CATCH 
		SET @ketqua = '1'
	commit TRANSACTION  
END
GO
--Dirty Read
/* Sửa thông tin cá nhân
 Input: thông tin người dùng
 Output: @Result */
 --Tạo fulltext index index
 CREATE UNIQUE INDEX PK_UsersSearch ON Users(MaNguoiDung)  
CREATE FULLTEXT CATALOG users_catalog  
GO  
CREATE FULLTEXT INDEX ON users  
 (   
  MaNguoiDung  
     Language 1033,  
  HoTen 
     Language 1033,  
  Email 
     Language 1033,
  DiaChi
	 Language 1033 ,
  LoaiNguoiDung
	 Language 1033  
 )   
  KEY INDEX PK_UsersSearch  
      ON users_catalog   
GO  

CREATE PROCEDURE SuaThongTinCaNhan 
	@MaNguoiDung char(5),
	@HoTen nvarchar(31),
	@Email nvarchar(50),
	@SDT varchar(11),
	@DiaChi varchar(50),
	@Result int output
AS
BEGIN
	BEGIN TRANSACTION UPDATEPrivateInf
		SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED 
		BEGIN TRY 
			IF @MaNguoiDung NOT in (SELECT MaNguoiDung FROM Users WHERE TinhTrang = 1)
			BEGIN
				RAISERROR (N'Người dùng không tồn tại', 16, 1)
				SET @Result = 0
				RETURN
			END
			UPDATE Users SET HoTen = @HoTen, Email = @Email, SDT = @SDT, DiaChi = @DiaChi WHERE MaNguoiDung = @MaNguoiDung
			WAITFOR DELAY '00:00:10'
			IF EXISTS (SELECT * FROM USERS WHERE Email = @Email)
				BEGIN
					ROLLBACK TRAN
					RETURN
				END
		END TRY
		BEGIN CATCH
			
			DECLARE @ErrorMessage NVARCHAR(4000)
			DECLARE @ErrorSeverity INT
			DECLARE @ErrorState INT
 
			SELECT @ErrorMessage = ERROR_MESSAGE(),
				   @ErrorSeverity = ERROR_SEVERITY(),
				   @ErrorState = ERROR_STATE()
			RAISERROR (@ErrorMessage, @ErrorSeverity, @ErrorState)
			ROLLBACK TRANSACTION UPDATEPrivateInf
			SET @Result = 0
			RETURN
		END CATCH
		SET @Result = 1
	commit TRANSACTION UPDATEPrivateInf
END

GO
CREATE PROCEDURE XemDSNguoiDung @tuKhoa nvarchar(100)
AS
BEGIN
	BEGIN TRANSACTION XemNguoiDung
		SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED 
		BEGIN TRY
			SELECT * FROM Users WHERE CONTAINS((MaNguoiDung, HoTen, Email, DiaChi, LoaiNguoiDung), @tuKhoa) OR LoaiNguoiDung = @tuKhoa
		END TRY
		BEGIN CATCH
			DECLARE @ErrorMessage NVARCHAR(4000)
			DECLARE @ErrorSeverity INT
			DECLARE @ErrorState INT
 
			SELECT @ErrorMessage = ERROR_MESSAGE(),
				   @ErrorSeverity = ERROR_SEVERITY(),
				   @ErrorState = ERROR_STATE()
			RAISERROR (@ErrorMessage, @ErrorSeverity, @ErrorState)
			ROLLBACK TRANSACTION XemNguoiDung
			RETURN
		END CATCH
	COMMIT TRANSACTION XemNguoiDung
END


DROP INDEX PK_UsersSearch on Users
drop fulltext index on users
