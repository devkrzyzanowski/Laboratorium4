package pl.devkrzyzanowski.laboratorium4;

public class Opinion {
    private Rate rate;
    private Author author;

    public Opinion() {
        rate = new Rate();
        author = new Author();
    }


    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
