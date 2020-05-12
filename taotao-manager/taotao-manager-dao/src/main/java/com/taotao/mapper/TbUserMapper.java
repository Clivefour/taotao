package com.taotao.mapper;


import com.taotao.pojo.TbUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TbUserMapper {
    @Select("SELECT count(*) FROM tbuser WHERE userName = #{param}")
    int checkUserName(String param);
    @Select("SELECT count(*) FROM tbuser WHERE phone = #{param}")
    int checkPhone(String param);
    @Select("SELECT count(*) FROM tbuser WHERE email = #{param}")
    int checkEmail(String param);
    @Insert("INSERT INTO tbuser(userName, passWord, phone, email, created, updated) VALUE (#{userName},#{passWord},#{phone},#{email},#{created},#{updated})")
    int addUser(TbUser tbUser);
    @Select("SELECT * FROM tbuser WHERE userName = #{userName} AND passWord = #{passWord}")
    TbUser findUserByNameAndPass(@Param("userName") String userName, @Param("passWord")String passWord);
}