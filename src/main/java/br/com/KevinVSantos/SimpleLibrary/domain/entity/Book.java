package br.com.KevinVSantos.SimpleLibrary.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String author;

    @JoinColumn(name = "mainGenreTitle")
    @NotBlank
    private Long mainGenreTitle;

    @JsonIgnore
    @ManyToOne( targetEntity = Genre.class)
    @JoinColumn(name = "mainGenreTitle", insertable = false, updatable = false)
    private Genre mainGenre;

    @JoinColumn(name = "subGenreTitle")
    @NotBlank
    private Long subGenreTitle;

    @JsonIgnore
    @ManyToOne( targetEntity = SubGenre.class)
    @JoinColumn(name = "subGenreTitle", insertable = false, updatable = false)
    private SubGenre subGenre;

    private String type;

    @Column(scale = 2)
    @NotBlank
    private BigDecimal price;

    @Column(precision = 4, scale = 2)
    private BigDecimal rate;

    private int reviews;

    private String url;

    @AssertTrue(message = "must greater than zero.")
    public boolean isPrice(){
        return this.getPrice().compareTo(BigDecimal.ZERO) >= 0;
    }
}
