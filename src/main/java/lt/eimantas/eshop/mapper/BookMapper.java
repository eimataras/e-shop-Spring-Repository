package lt.eimantas.eshop.mapper;

import lt.eimantas.eshop.model.Book;
import lt.eimantas.eshop.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BookMapper {

    @Select("SELECT * FROM `Book`")
    List<Book> findAll();

    @Select("SELECT * FROM `Book` WHERE `bookId`=#{bookId}")
    Optional<Book> findById(Integer bookId);

    @Select("SELECT * FROM `Book` ORDER BY bookId DESC LIMIT 1")
    Optional<Book> findByMaxId();

    @Insert("INSERT INTO `Book` (`title`, `author`, `publishedDate`, `bookCover`, `quantity`) VALUES (#{title}, #{author}, #{publishedDate}, #{bookCover}, #{quantity})")
//    @SelectKey(statement = "SELECT SCOPE_IDENTITY()", keyProperty = "id", before = false, resultType = Integer.class)
    void add(Book book);

    @Update("UPDATE `Book` SET `title`=#{title}, `author`=#{author}, `publishedDate`=#{publishedDate}, `bookCover`=#{bookCover}, `quantity`=#{quantity} WHERE `bookId`=#{bookId}")
    void update(Book book);

    @Delete("DELETE FROM `Book` WHERE `bookId` = #{bookId}")
    void deleteById(Integer bookId);
}