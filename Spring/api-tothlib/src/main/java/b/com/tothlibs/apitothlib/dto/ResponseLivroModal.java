package b.com.tothlibs.apitothlib.dto;

public class ResponseLivroModal {

    String title;
    String publishedDate;
    String description;
    Double amount;
    String capa;
    String smallThumbnail;
    String thumbnail;
    String autor;
    String disponivel;
    String avaliacao;
    String forSale;
    Integer idLivro;

    public ResponseLivroModal(String title,
                              String publishedDate,
                              String description,
                              Double amount,
                              String capa,
                              String smallThumbnail,
                              String thumbnail,
                              String autor,
                              String disponivel,
                              String avaliacao,
                              String forSale,
                              Integer idLivro) {
        this.title = title;
        this.publishedDate = publishedDate;
        this.description = description;
        this.amount = amount;
        this.capa = capa;
        this.smallThumbnail = smallThumbnail;
        this.thumbnail = thumbnail;
        this.autor = autor;
        this.disponivel = disponivel;
        this.avaliacao = avaliacao;
        this.forSale = forSale;
        this.idLivro = idLivro;
    }

    public ResponseLivroModal() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    public String getSmallThumbnail() {
        return smallThumbnail;
    }

    public void setSmallThumbnail(String smallThumbnail) {
        this.smallThumbnail = smallThumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(String disponivel) {
        this.disponivel = disponivel;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getForSale() {
        return forSale;
    }

    public void setForSale(String forSale) {
        this.forSale = forSale;
    }

    public Integer getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Integer idLivro) {
        this.idLivro = idLivro;
    }

    @Override
    public String toString() {
        return  "\ntitle='" + title +
                "\npublishedDate='" + publishedDate +
                "\ndescription='" + description +
                "\namount=" + amount +
                "\ncapa='" + capa +
                "\nsmallThumbnail='" + smallThumbnail +
                "\nthumbnail='" + thumbnail +
                "\nautor='" + autor +
                "\ndisponivel='" + disponivel +
                "\navaliacao='" + avaliacao +
                "\nFor sale='" + forSale +
                "\nidLivro='" + idLivro;
    }
}
