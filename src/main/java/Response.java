public class Response {

    private String author;
    private String subTitle;
    private String message;
    private Grade grade;


    public Response(String author, String subTitle, String message, Grade grade) {
        this.author = author;
        this.subTitle = subTitle;
        this.message = message;
        this.grade = grade;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Автор ='" + author + '\'' +
                ", Название ='" + subTitle + '\'' +
                ", Рецензия ='" + message + '\'' +
                ", Оценка =" + grade;
    }
}
