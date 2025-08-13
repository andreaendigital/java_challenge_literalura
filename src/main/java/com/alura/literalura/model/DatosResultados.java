package com.alura.literalura.model;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
//Le dice a Jackson que ignore las propiedades del JSON que no se han definido en la clase. Esto evita errores si la API tiene más campos de los que se necesitan.

public class DatosResultados {
    private List<DatosLibro> results;

    public List<DatosLibro> getResults() {
        return results;
    }

    public void setResults(List<DatosLibro> results) {
        this.results = results;
    }
}
