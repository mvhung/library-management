-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authors` (
  `au_id` int NOT NULL AUTO_INCREMENT,
  `au_fullname` varchar(255) NOT NULL,
  `au_image` varchar(255) DEFAULT NULL,
  `au_introduce` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`au_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` VALUES (1,'Nguyễn Hữu Việt Hưng','https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/authors/4d07e738-d23e-4f14-9be8-ce47658157e7_maiviethung.jpg','Nhà giáo nhân dân','2023-08-14 22:17:11.509478','admin2@mail.com','2023-08-14 22:19:41.656222','unknown'),(2,'Ngô Quốc Anh','https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/authors/9376a5f3-8ddc-473f-909a-fd21ec8c4cca_ngoquocanh.jpg','Nhà giáo ưu tú','2023-08-14 22:17:11.518480','admin2@mail.com','2023-08-14 22:23:45.514537','unknown'),(3,'Đỗ Trung Kiên','https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/authors/c768ee62-db28-49bb-a065-3b4aa9094473_avatar.jpg','Nhà giáo nhân dân','2023-08-14 22:25:38.382611','admin2@mail.com','2023-08-14 22:28:09.809413','unknown'),(4,'Đào Quốc Hội','https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/authors/e04a3ed4-354d-4b52-a02f-c46d1e304c7f_dao-quoc-hoi.png','Nhà giáo ưu tú','2023-08-14 22:25:38.396620','admin2@mail.com','2023-08-14 22:30:15.696821','unknown'),(5,'Gosho Aoyama','https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/authors/037f8495-3659-415f-bc5a-5aaa99eadbf9_conan.jpg','Họa sĩ Nhật Bản','2023-08-14 22:36:00.142756','admin2@mail.com','2023-08-14 22:41:40.712660','unknown'),(6,' Fujiko F.Fujio','https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/authors/da240112-1a29-42e7-8ade-10883c5bd186_doraemon2.jpg','Họa sĩ Nhật Bản','2023-08-14 22:43:28.396423','admin2@mail.com','2023-08-14 22:46:51.486629','unknown'),(7,' Trần Văn Lượng','https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/authors/eb73ff80-0bc2-4bac-87f8-776f1b2a0fbb_img7896-15742207413491775630746.jpg','Giáo Sư','2023-08-14 22:51:40.581080','admin2@mail.com','2023-08-14 23:00:56.743388','unknown'),(8,' Lý Quang Linh','https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/authors/7654d1b1-5fc1-411f-9930-fb4da9e727c4_edit-nvc-16764798063741126533163.png','Nhà giáo','2023-08-14 22:51:40.594085','admin2@mail.com','2023-08-14 23:00:16.607706','unknown'),(9,' Trần Thị Ngọc Dung','https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/authors/e1a9a3e2-8e66-406f-900f-cf5c8075d1e8_aspiring-hr-professional.jpg','Nhà giáo','2023-08-14 22:51:40.602081','admin2@mail.com','2023-08-14 22:58:30.051871','unknown'),(10,' Nguyễn Khanh','https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/authors/debb2400-d535-4b2e-817a-17a62e1a0ae4_nguyen-khanh-885.jpg','Giáo Sư','2023-08-14 23:06:14.666344','admin2@mail.com','2023-08-14 23:08:40.910056','unknown');
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_author`
--


DROP TABLE IF EXISTS `book_author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_author` (
  `bo_id` int NOT NULL,
  `au_id` int NOT NULL,
  FOREIGN KEY (`bo_id`) REFERENCES `books`(`bo_id`),
  FOREIGN KEY (`au_id`) REFERENCES `authors`(`au_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

-- -- Dumping data for table `book_author`
-- --

LOCK TABLES `book_author` WRITE;
/*!40000 ALTER TABLE `book_author` DISABLE KEYS */;
INSERT INTO `book_author` VALUES (1,1),(1,2),(2,3),(2,4),(3,3),(3,4),(4,5),(5,6),(6,7),(6,8),(6,9),(7,7),(7,8),(7,9),(8,7),(8,8),(8,9),(9,10),(10,10);
/*!40000 ALTER TABLE `book_author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `bo_id` int NOT NULL AUTO_INCREMENT,
  `bo_description` varchar(255) DEFAULT NULL,
  `bo_image` varchar(255) DEFAULT NULL,
  `bo_published_year` int NOT NULL,
  `bo_quantity` int NOT NULL,
  `bo_title` varchar(255) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `ca_id` int DEFAULT NULL,
  `pu_id` int DEFAULT NULL,
  PRIMARY KEY (`bo_id`),
  KEY `FKh4foxq2tyftak11t7473vkxr1` (`ca_id`),
  KEY `FK8yocap96q9elukq14d4stsb74` (`pu_id`),
  CONSTRAINT `FK8yocap96q9elukq14d4stsb74` FOREIGN KEY (`pu_id`) REFERENCES `publishers` (`pu_id`),
  CONSTRAINT `FKh4foxq2tyftak11t7473vkxr1` FOREIGN KEY (`ca_id`) REFERENCES `categories` (`ca_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'Đại số mang tâm hồn tuyến tính','https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/books/eacebb30-fa85-494e-8215-94d2dac436a8_daisotuyentinh.jpg',2023,100,'Đại số tuyến tính','2023-08-14 22:17:11.530480','admin2@mail.com','2023-08-14 22:17:45.501905','admin2@mail.com',1,1),(2,'Giải tích mang tâm hồn toán học','https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/books/56f9b38f-a623-46b7-a4a7-861905a27865_giaitich1.png',2023,100,'Giải tích 1','2023-08-14 22:25:38.407615','admin2@mail.com','2023-08-14 22:32:08.625668','admin2@mail.com',1,1),(3,'Giải tích mang tâm hồn toán học','https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/books/90ad4c08-5257-4d3b-a69f-213642e2d608_giao-trinh-giai-tich-2-0-367.jpg',2023,100,'Giải tích 2','2023-08-14 22:30:51.153813','admin2@mail.com','2023-08-14 22:31:58.143123','admin2@mail.com',1,1),(4,'Trinh thám hâp dẫn đến từng dòng','https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/books/f8f0fc99-eaed-4d64-ade8-a50f0498d302_conan.jpg',2023,200,'Thám tử lừng danh conan','2023-08-14 22:36:00.147757','admin2@mail.com','2023-08-14 22:37:28.720660','admin2@mail.com',2,2),(5,'Chú mèo máy đến từ tương lai','https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/books/d2c3ec1e-37dc-423d-bba0-c3482f1c60e7_doraemon.png',2023,200,'Doraemon','2023-08-14 22:43:28.400425','admin2@mail.com','2023-08-14 22:45:10.656395','admin2@mail.com',3,2),(6,'Vật lý cơ bản đến nâng cao ','https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/books/f191958c-da43-4cf5-985d-c324ed4b9a50_vat-ly-dai-cuong-pham-thi-cuc.jpg',2023,300,'Vật lý đại cương 1','2023-08-14 22:51:40.609082','admin2@mail.com','2023-08-14 22:52:17.397061','admin2@mail.com',4,3),(7,'Vật lý cơ bản đến nâng cao ','https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/books/1a3afd42-e989-4520-96f1-3c3a1bd7fba5_b4__34756.jpg',2023,300,'Vật lý đại cương 2','2023-08-14 23:01:39.648669','admin2@mail.com','2023-08-14 23:03:02.505294','admin2@mail.com',4,3),(8,'Vật lý cơ bản đến nâng cao ','https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/books/8085626e-93e4-4c89-8fdc-8ba14c6ccefe_wpViewImagePatron.jpg',2023,300,'Vật lý đại cương 3','2023-08-14 23:01:45.131543','admin2@mail.com','2023-08-14 23:03:45.334674','admin2@mail.com',4,3),(9,'Hóa học cơ bản đến nâng cao ','https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/books/11f66676-581c-4335-8de6-17d4fe10ab7a_2e7381dc117829350956af598b77523d.jpg',2023,300,'Hóa Học đại cương 1','2023-08-14 23:06:14.671380','admin2@mail.com','2023-08-14 23:06:37.864045','admin2@mail.com',5,4),(10,'Hóa học cơ bản đến nâng cao ','https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/books/a637fdf2-f4c2-4750-97c9-f5ed1f19e95b_b4__95285_image2_800_big.jpg',2023,300,'Hóa Học đại cương 2','2023-08-14 23:09:17.278825','admin2@mail.com','2023-08-14 23:10:29.273394','admin2@mail.com',5,4);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `ca_id` int NOT NULL AUTO_INCREMENT,
  `ca_description` varchar(255) DEFAULT NULL,
  `ca_name` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ca_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Sách phục vụ khoa học','Sách Toán học','2023-08-14 22:17:11.465483','admin2@mail.com','2023-08-14 22:17:11.465483','admin2@mail.com'),(2,'Truyện trinh thám hay tiểu thuyết trinh thám là một tiểu loại của tiểu thuyết phiêu lưu. ','Truyện trinh thám','2023-08-14 22:36:00.127759','admin2@mail.com','2023-08-14 22:36:00.127759','admin2@mail.com'),(3,'Doraemon is a fictional character in the Japanese manga and anime series of the same name created by Fujiko F. Fujio. Doraemon is a male robotic earless cat that travels back in time from the 22nd century to aid a preteen boy named Nobita. ','Truyện phiêu lưu','2023-08-14 22:43:28.383428','admin2@mail.com','2023-08-14 22:43:28.383428','admin2@mail.com'),(4,'Bài giảng Vật lý Đại cương 01 - Viện Vật lý kỹ thuật  ','Sách Vật Lý Học','2023-08-14 22:51:40.566079','admin2@mail.com','2023-08-14 22:51:40.566079','admin2@mail.com'),(5,'Bài giảng Hóa học Đại cương  ','Sách Hóa Học','2023-08-14 23:06:14.651339','admin2@mail.com','2023-08-14 23:06:14.651339','admin2@mail.com');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publishers`
--

DROP TABLE IF EXISTS `publishers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publishers` (
  `pu_id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `pu_image` varchar(255) DEFAULT NULL,
  `pu_introduce` varchar(255) DEFAULT NULL,
  `pu_name` varchar(45) NOT NULL,
  `pu_website` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publishers`
--

LOCK TABLES `publishers` WRITE;
/*!40000 ALTER TABLE `publishers` DISABLE KEYS */;
INSERT INTO `publishers` VALUES (1,'2023-08-14 22:17:11.492480','admin2@mail.com','https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/publishers/4d949354-d912-4099-86c2-81592c553da4_daihocquocgiahanoi.png','Là 1 nhà xuất bản lớn','Nhà xuất bản Đại học Quôc gia','https://press.vnu.edu.vn/','2023-08-14 22:19:21.849198','admin2@mail.com'),(2,'2023-08-14 22:36:00.133760','admin2@mail.com','https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/publishers/7002740c-b59a-4bcf-9c8b-e1a4880e527b_Logo_nxb_Kim_Đồng.png','Là 1 nhà xuất bản lớn','Nhà xuất bản Kim Đồng','https://nxbkimdong.com.vn/','2023-08-14 22:40:17.374544','admin2@mail.com'),(3,'2023-08-14 22:51:40.575079','admin2@mail.com','https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/publishers/b2875ba6-52cb-469b-9523-cb63c1bb4a46_fav-01.png','Là 1 nhà xuất bản lớn','Nhà xuất bản Đại Học Quốc Gia Hồ Chí Minh','https://vnuhcmpress.edu.vn/','2023-08-14 22:56:04.659970','admin2@mail.com'),(4,'2023-08-14 23:06:14.660348','admin2@mail.com','https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/publishers/919c1626-6256-45b9-a4bf-22262b56ece0_daihocquocgiahanoi.png','Là 1 nhà xuất bản lớn','Nhà xuất bản Đại Học Quốc Gia Hà Nội','https://press.vnu.edu.vn/','2023-08-14 23:07:31.482422','admin2@mail.com');
/*!40000 ALTER TABLE `publishers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token` (
  `id` int NOT NULL AUTO_INCREMENT,
  `expired` bit(1) NOT NULL,
  `revoked` bit(1) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `token_type` enum('BEARER') DEFAULT NULL,
  `us_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pddrhgwxnms2aceeku9s2ewy5` (`token`),
  KEY `FKqgs10p9tf2emeas138ej31oy3` (`us_id`),
  CONSTRAINT `FKqgs10p9tf2emeas138ej31oy3` FOREIGN KEY (`us_id`) REFERENCES `users` (`us_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (1,_binary '\0',_binary '\0','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbjFAbWFpbC5jb20iLCJpYXQiOjE2OTIwMjYxOTUsImV4cCI6MTY5MjExMjU5NX0.N037a8R-DY40TfCIb_HI3MEwrLJeuxePIaT-HvL_A4s','BEARER',1),(2,_binary '\0',_binary '\0','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbjJAbWFpbC5jb20iLCJpYXQiOjE2OTIwMjYxOTUsImV4cCI6MTY5MjExMjU5NX0.YLjUmNNdkrsyGMruu6d5AvYXrk8kO8lt017xSc3BoQc','BEARER',2),(3,_binary '\0',_binary '\0','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0dWFuMTIzOTg3QGdtYWlsLmNvbSIsImlhdCI6MTY5MjAyOTcyMiwiZXhwIjoxNjkyMTE2MTIyfQ.j67YeTDnzjIqeWCjL_P8WCx_X8DuDFF_ghJNsqDN2SU','BEARER',3),(4,_binary '\0',_binary '\0','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0dWFucGhhbjEyM0BnbWFpbC5jb20iLCJpYXQiOjE2OTIwMjk5MDYsImV4cCI6MTY5MjExNjMwNn0.5phaON1yldcQsT1n-i1pxaCJW_TN7GbLUDNAT0VZoh0','BEARER',4),(5,_binary '\0',_binary '\0','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJodW5nbWFpMTIzQGdtYWlsLmNvbSIsImlhdCI6MTY5MjAyOTk1NCwiZXhwIjoxNjkyMTE2MzU0fQ.6Sl5dn88HdDdiz8MGGA2lMzSlRREYa-jbWYwwFUzuhk','BEARER',5),(6,_binary '\0',_binary '\0','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJodW5ndmlldEBnbWFpbC5jb20iLCJpYXQiOjE2OTIwMzAwNjksImV4cCI6MTY5MjExNjQ2OX0.eUiqSztgpJ34O8C3RB80syldsZaiBxbpSfDSJSzzYfE','BEARER',6),(7,_binary '\0',_binary '\0','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2aWV0aHVuZzEyQGdtYWlsLmNvbSIsImlhdCI6MTY5MjAzMDIwMiwiZXhwIjoxNjkyMTE2NjAyfQ.XVcmYIRbIoNeXX6fnQWzrKlfGtWhF_7tTOj4EU091-Q','BEARER',7),(8,_binary '\0',_binary '\0','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYW9xdW9jaG9pQGdtYWlsLmNvbSIsImlhdCI6MTY5MjAzMDI4MCwiZXhwIjoxNjkyMTE2NjgwfQ._FE2YsgOGqRPR14Wt7NHLAHC8cgDl1i3qqzk9SCRkhE','BEARER',8),(9,_binary '\0',_binary '\0','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYW9tYWlAZ21haWwuY29tIiwiaWF0IjoxNjkyMDMwMzA1LCJleHAiOjE2OTIxMTY3MDV9.ytnKNbbib3R6d0KnqhzvsspnSAAtupY0U2r_3Gx2q-s','BEARER',9),(10,_binary '\0',_binary '\0','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ4dWFudGhpZW5AZ21haWwuY29tIiwiaWF0IjoxNjkyMDMwMzI2LCJleHAiOjE2OTIxMTY3MjZ9.YpnDn_YFjSku1R0p8Uoa15m4aGeFlo95N-dMVMxWcNQ','BEARER',10),(11,_binary '\0',_binary '\0','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ4dWFudHJ1b25nQGdtYWlsLmNvbSIsImlhdCI6MTY5MjAzMDM1MCwiZXhwIjoxNjkyMTE2NzUwfQ.rh8y9MVkwzf3y6z4epHRvJ23DilkOGw3gOeZFzsw8_c','BEARER',11);
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `us_id` int NOT NULL AUTO_INCREMENT,
  `us_address` varchar(255) DEFAULT NULL,
  `us_avatar` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `us_email` varchar(255) NOT NULL,
  `us_first_name` varchar(255) DEFAULT NULL,
  `us_last_name` varchar(255) DEFAULT NULL,
  `us_password` varchar(255) NOT NULL,
  `us_role` enum('ADMIN','USER') DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `us_username` varchar(255) NOT NULL,
  PRIMARY KEY (`us_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,NULL,'https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/avatars/c14ae6d1-7404-4df7-8b2e-8f4cc1c0a49a_20001987.jpg','2023-08-14 22:16:35.733509','admin1@mail.com','Admin','Admin','$2a$10$RcM3rVldEOGPChVNvmsSz.4XZ0esK5PZIRKGo9oePT6qaV4PW4DWa','ADMIN','2023-08-14 23:17:29.000394','Admin 1'),(2,NULL,'https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/avatars/864250b9-c994-48da-a680-0c13863fc6da_20001987.jpg','2023-08-14 22:16:35.955280','admin2@mail.com','Admin','Admin','$2a$10$MNy4tAkn2afoFYeakstiZuNt4RsnopXsAUvl6pZOtqnSrwhAHSAEa','ADMIN','2023-08-14 23:17:19.119797','Admin 2'),(3,'Nam Dinh','https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/avatars/895698dc-9c15-4255-ad96-6ccb409323ed_avatar.jpg','2023-08-14 23:15:22.115405','tuan123987@gmail.com','Tuan','Phan','$2a$10$.XQVpxObpYG1YQnJ4ala1.bTh6UGXK8f.wziyN.vGk9dOZX3UTiNq','USER','2023-08-14 23:17:05.413960','ptuan123987'),(4,'Nam Dinh','https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/avatars/d1e89ab3-96a9-4a15-93bc-0a5ae439498e_mAKI-top-anh-dai-dien-dep-chat-1.jpg','2023-08-14 23:18:26.667428','tuanphan123@gmail.com','Tuan','Phan','$2a$10$tyO6MhX3L8F5xcALaCsBx.VQc3zzBuo3U5q1QZ4obaHK0JBOjDcDa','USER','2023-08-14 23:18:43.715935','tuantt'),(5,'Tuyen Quang','https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/avatars/5766eed2-2974-49a0-84d2-6d7f378cc650_hinh-anh-trai-k7-k8-2k7-2k8-1.jpg','2023-08-14 23:19:14.030492','hungmai123@gmail.com','Hung','Mai','$2a$10$lUfGKWxulQ70ChrXDL3GGOlJu9r0ey9fYg7nxRV9KDLbErXK/3W5m','USER','2023-08-14 23:20:32.370274','hungmai'),(6,'Tuyen Quang','https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/avatars/b9cde5db-7734-4db3-9a83-96fd754d0242_Anh-trai-dep-anh-trai-dep-dau-nam-ngoi-tren-bai-co.jpg','2023-08-14 23:21:09.457220','hungviet@gmail.com','Hung','Viet','$2a$10$1lzq1okQsVWaffC93dXzKeezTyWNQvsRMUeToNpS78.NXIRUD.mwu','USER','2023-08-14 23:22:26.741737','hungviet'),(7,'Hà Nội','https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/avatars/89a7825c-8b62-4aea-8a0e-014685a50033_anh-trai-dep-che-mat-2.jpg','2023-08-14 23:23:22.792151','viethung12@gmail.com','Việt','Hưng','$2a$10$9CRX6bi6iWimIzjSdIxgkeN4h/.OYCK/pxLYGQi6dIHCsLw4ejAHa','USER','2023-08-14 23:24:00.321519','viethung'),(8,'Hà Nam','https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/avatars/cd9a2ec6-9324-4b7b-9ddb-55ac591e2ab2_hinh-anh-trai-dep-dau-nam-deo-kinh.jpg','2023-08-14 23:24:40.909567','daoquochoi@gmail.com','Hội','Quốc','$2a$10$nAfJrarLChlUhnmGgvPFdezVDeZWeyJslK0baOjiL4SyNX/PhUhfS','USER','2023-08-14 23:26:34.922352','quochoi12'),(9,'Hà Nam','https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/avatars/73089456-4b67-42a2-8f99-b6e2409f8224_3f15af00815adebe9164d9015d3979ca.jpg','2023-08-14 23:25:05.354696','daomai@gmail.com','Mai','Thanh','$2a$10$ydnzPZtB5HA10l5460wj0eNl3tSgmI46fFMwVnVqkpaQRENtA1t.C','USER','2023-08-14 23:27:14.895367','thanhmai'),(10,'Hà Nam','https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/avatars/f0f34100-d062-499c-a12a-6a5848c5ac60_edit-nvc-16764798063741126533163.png','2023-08-14 23:25:26.618757','xuanthien@gmail.com','Thiện','Thanh','$2a$10$zldvfNyD/prYj48PXEsAeuU76Fx26MqWQ2qyUiG5ZxjM4aR1k7o36','USER','2023-08-14 23:27:32.541328','thienstyle'),(11,'Nam Định','https://librarymanagementstorage.s3.ap-southeast-1.amazonaws.com/avatars/884a59fd-421c-4746-b401-b4268f842a78_conan.jpg','2023-08-14 23:25:50.823909','xuantruong@gmail.com','Trương','Thanh','$2a$10$MzpSGFlWVnLgr5Zt0JSVd.hGbD0AHAW3G4Y9Sfv5k.3nRhRIVH0ee','USER','2023-08-14 23:27:55.432402','truongbien');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-14 23:29:01
