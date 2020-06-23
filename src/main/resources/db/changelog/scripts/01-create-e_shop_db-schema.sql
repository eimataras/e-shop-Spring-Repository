CREATE TABLE IF NOT EXISTS Book
(
	bookId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(40) NOT NULL,
    CONSTRAINT Book_UK UNIQUE (title),
    author VARCHAR(40) NOT NULL,
    publishedDate VARCHAR(40),
    bookCover VARCHAR(100),
    quantity INT(11) NOT NULL
)
/
CREATE TABLE IF NOT EXISTS `User`
(
    userId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(40),
    surname VARCHAR(40),
    username VARCHAR(40) NOT NULL,
    CONSTRAINT User_UK UNIQUE (username),
    password VARCHAR(200) NOT NULL,
    uid VARCHAR(40) NOT NULL
)
/
CREATE TABLE IF NOT EXISTS OrderStatus
(
    statusId INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    type VARCHAR(40) NOT NULL
)
/
CREATE TABLE IF NOT EXISTS `Order`
(
	orderId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userId INT(11) NOT NULL,
    CONSTRAINT Order_FK1 FOREIGN KEY(userId) REFERENCES `User`(userId) ON DELETE CASCADE,
    statusId INT(11),
    CONSTRAINT Order_FK2 FOREIGN KEY(statusId) REFERENCES OrderStatus(statusId)
)
/
CREATE TABLE IF NOT EXISTS OrderItems
(
    orderItemId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    orderId INT(11) NOT NULL,
    CONSTRAINT OrderItems_FK1 FOREIGN KEY(orderId) REFERENCES `Order`(orderId) ON DELETE CASCADE,
    bookId INT(11) NOT NULL,
    CONSTRAINT OrderItems_FK2 FOREIGN KEY(bookId) REFERENCES Book(bookId) ON DELETE CASCADE,
    quantity INT(11) NOT NULL
)
/
CREATE TABLE IF NOT EXISTS `Role`
(
    roleId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    roleName VARCHAR(40) NOT NULL
)
/
CREATE TABLE IF NOT EXISTS UserRole
(
    userRoleId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userId INT(11) NOT NULL,
    CONSTRAINT UserRole_FK1 FOREIGN KEY(userId) REFERENCES `User`(userId) ON DELETE CASCADE,
    roleId INT(11) NOT NULL,
    CONSTRAINT UserRole_FK2 FOREIGN KEY(roleId) REFERENCES `Role`(roleId) ON DELETE CASCADE
)