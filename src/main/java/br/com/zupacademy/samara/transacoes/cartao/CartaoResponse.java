package br.com.zupacademy.samara.transacoes.cartao;

public class CartaoResponse {

    private String id;
    private String email;

    @Deprecated
    public CartaoResponse() {
    }

    public CartaoResponse(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Cartao toModel() {
        return new Cartao(id, email);
    }
}
