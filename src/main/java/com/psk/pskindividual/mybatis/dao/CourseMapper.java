package com.psk.pskindividual.mybatis.dao;

import com.psk.pskindividual.mybatis.model.Course;
import org.mybatis.cdi.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.COURSE
     *
     * @mbg.generated Sun Mar 10 15:34:33 EET 2024
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.COURSE
     *
     * @mbg.generated Sun Mar 10 15:34:33 EET 2024
     */
    int insert(Course row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.COURSE
     *
     * @mbg.generated Sun Mar 10 15:34:33 EET 2024
     */
    Course selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.COURSE
     *
     * @mbg.generated Sun Mar 10 15:34:33 EET 2024
     */
    List<Course> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.COURSE
     *
     * @mbg.generated Sun Mar 10 15:34:33 EET 2024
     */
    int updateByPrimaryKey(Course row);
}