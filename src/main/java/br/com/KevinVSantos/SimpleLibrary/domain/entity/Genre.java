package br.com.KevinVSantos.SimpleLibrary.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Genre extends AbstractEntity<String> {

    @Id
    @NotBlank
    private String title;

    private String url;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "mainGenreTitle", insertable = false, updatable = false)
    private List<SubGenre> subGenres;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "mainGenreTitle", insertable = false, updatable = false)
    private List<Book> books;

    @Override
    @JsonIgnore
    public String getGenericId() {
        return this.getTitle();
    }
}
