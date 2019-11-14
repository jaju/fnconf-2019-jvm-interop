package co.gywb.demo.web_front.java;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album {
    public String number;
    public String year;
    public String album;
    public String artist;
    public String genre;
    public String subgenre;
}
