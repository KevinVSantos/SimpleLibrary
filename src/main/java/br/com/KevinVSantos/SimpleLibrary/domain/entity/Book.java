package br.com.KevinVSantos.SimpleLibrary.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book extends AbstractEntity<String>{

    @Id
    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @JoinColumn(name = "mainGenreTitle")
    @NotBlank
    private String mainGenreTitle;

    @JsonIgnore
    @ManyToOne( targetEntity = Genre.class)
    @JoinColumn(name = "mainGenreTitle", insertable = false, updatable = false)
    private Genre mainGenre;

    @JoinColumn(name = "subGenreTitle")
    @NotBlank
    private String subGenreTitle;

    @JsonIgnore
    @ManyToOne( targetEntity = SubGenre.class)
    @JoinColumn(name = "subGenreTitle", insertable = false, updatable = false)
    private SubGenre subGenre;

    private String type;

    @Column(scale = 2)
    @NotNull
    private BigDecimal price;

    @Column(precision = 4, scale = 2)
    private BigDecimal rate;

    @Min(value = 0)
    private Long reviews;

    private String url;

    @AssertTrue(message = "must be greater than zero.")
    public boolean isPrice(){
        return this.getPrice().compareTo(BigDecimal.ZERO) >= 0;
    }

    @AssertTrue(message = "must be between zero than ten.")
    public boolean isRate(){
        return this.getRate().compareTo(BigDecimal.ZERO) >= 0 && this.getRate().compareTo(BigDecimal.TEN) <= 0;
    }

    @Override
    @JsonIgnore
    public String getGenericId() {
        return this.getTitle();
    }
}
