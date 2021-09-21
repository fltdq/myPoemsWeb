package com.shici.pojo;

/**
 * FileName: Poem
 * Description:
 * Author: CSH
 * Date: 2021/1/4 15:25
 * Version: 1.0
 */
public class Poem {
    private Integer id;
    private String name;
    private String dynasty;
    private String author;
    private String first;
    private String second;
    private String third;
    private String fourth;
    private String fifth;
    private String sixth;
    private String seventh;
    private String eighth;

    public Poem() {
    }

   /* public Poem(Integer id, String name, String dynasty, String author, String first, String second, String third, String fourth) {
        this.id = id;
        this.name = name;
        this.dynasty = dynasty;
        this.author = author;
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
    }*/

    public Poem(Integer id, String name, String dynasty, String author, String first, String second, String third, String fourth, String fifth, String sixth, String seventh, String eighth) {
        this.id = id;
        this.name = name;
        this.dynasty = dynasty;
        this.author = author;
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;
        this.sixth = sixth;
        this.seventh = seventh;
        this.eighth = eighth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getThird() {
        return third;
    }

    public void setThird(String third) {
        this.third = third;
    }

    public String getFourth() {
        return fourth;
    }

    public void setFourth(String fourth) {
        this.fourth = fourth;
    }

    public String getFifth() {
        return fifth;
    }

    public void setFifth(String fifth) {
        this.fifth = fifth;
    }

    public String getSixth() {
        return sixth;
    }

    public void setSixth(String sixth) {
        this.sixth = sixth;
    }

    public String getSeventh() {
        return seventh;
    }

    public void setSeventh(String seventh) {
        this.seventh = seventh;
    }

    public String getEighth() {
        return eighth;
    }

    public void setEighth(String eighth) {
        this.eighth = eighth;
    }

    @Override
    public String toString() {
        return "Poem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dynasty='" + dynasty + '\'' +
                ", author='" + author + '\'' +
                ", first='" + first + '\'' +
                ", second='" + second + '\'' +
                ", third='" + third + '\'' +
                ", fourth='" + fourth + '\'' +
                ", fifth='" + fifth + '\'' +
                ", sixth='" + sixth + '\'' +
                ", seventh='" + seventh + '\'' +
                ", eighth='" + eighth + '\'' +
                '}';
    }
}
