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

    @Select("SELECT * FROM `Book` WHERE `book_id`=#{book_id}")
    Optional<Book> findById(Integer book_id);

    @Select("SELECT * FROM `Book` ORDER BY book_id DESC LIMIT 1")
    Optional<Book> findByMaxId();

    @Insert("INSERT INTO `Book` (`title`, `author`, `published_date`, `book_cover`, `quantity`) VALUES (#{title}, #{author}, #{published_date}, #{book_cover}, #{quantity})")
//    @SelectKey(statement = "SELECT SCOPE_IDENTITY()", keyProperty = "id", before = false, resultType = Integer.class)
    void add(Book book);

    @Update("UPDATE `Book` SET `title`=#{title}, `author`=#{author}, `published_date`=#{published_date}, `book_cover`=#{book_cover}, `quantity`=#{quantity} WHERE `book_id`=#{book_id}")
    void update(Book book);

    @Delete("DELETE FROM `Book` WHERE `book_id` = #{book_id}")
    void deleteById(Integer book_id);
}