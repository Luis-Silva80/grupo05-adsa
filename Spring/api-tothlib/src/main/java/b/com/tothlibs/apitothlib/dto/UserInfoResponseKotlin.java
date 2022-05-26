package b.com.tothlibs.apitothlib.dto;

import java.lang.reflect.GenericArrayType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class UserInfoResponseKotlin {

    private Integer id;
    private String email;
    private String nomeUser;
    private Boolean statusAtivo;
    private List<UserModalResponse> pendencias;

    public UserInfoResponseKotlin(Integer id, String email, String nomeUser, Boolean statusAtivo, List<UserModalResponse> pendencias) {
        this.id = id;
        this.email = email;
        this.nomeUser = nomeUser;
        this.statusAtivo = statusAtivo;
        this.pendencias = pendencias;
    }

    public UserInfoResponseKotlin() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeUser() {
        return nomeUser;
    }

    public void setNomeUser(String nomeUser) {
        this.nomeUser = nomeUser;
    }

    public Boolean getStatusAtivo() {
        return statusAtivo;
    }

    public void setStatusAtivo(Boolean statusAtivo) {
        this.statusAtivo = statusAtivo;
    }

    public List<UserModalResponse> getPendencias() {
        return pendencias;
    }

    public void setPendencias(List<UserModalResponse> pendencias) {
        this.pendencias = pendencias;
    }
}
