package br.com.KevinVSantos.SimpleLibrary.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
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
    private String title;

    private String url;

    @OneToMany
    @JoinColumn(name = "mainGenreTitle", insertable = false, updatable = false)
    private List<SubGenre> subGenres;

    @Override
    public String getGenericId() {
        return this.getTitle();
    }
}
