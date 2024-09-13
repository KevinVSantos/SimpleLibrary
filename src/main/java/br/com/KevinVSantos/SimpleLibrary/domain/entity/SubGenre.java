package br.com.KevinVSantos.SimpleLibrary.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubGenre extends AbstractEntity<String>{

    @Id
    private String title;

    private String url;

    @JoinColumn(name = "mainGenreTitle")
    @NotBlank
    private String mainGenreTitle;

    @JsonIgnore
    @ManyToOne( targetEntity = Genre.class)
    @JoinColumn(name = "mainGenreTitle", insertable = false, updatable = false)
    private Genre mainGenre;

    @Override
    public String getGenericId() {
        return this.getTitle();
    }
}
