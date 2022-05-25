package b.com.tothlibs.apitothlib.dto;

import java.lang.reflect.GenericArrayType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class UserInfoResponseKotlin {

    private Integer id;
    private String email;
    private List<LocalDate> pendencias;

    public UserInfoResponseKotlin(Integer id, String email, List<LocalDate> pendencias) {
        this.id = id;
        this.email = email;
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

    public List<LocalDate> getPendencias() {
        return pendencias;
    }

    public void setPendencias(List<LocalDate> pendencias) {
        this.pendencias = pendencias;
    }

    @Override
    public String toString() {
        return "UserInfoResponseKotlin{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", pendencias=" + pendencias +
                '}';
    }
}
