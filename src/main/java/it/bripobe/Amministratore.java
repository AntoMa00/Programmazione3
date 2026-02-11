package it.bripobe;

import java.time.LocalDate;

public class Amministratore {
    public Amministratore (String username, String password, String nome, String cognome, LocalDate dataNascita,
                           char sesso, char nTel, String email){
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.sesso = sesso;
        this.nTel = nTel;
        this.email = email;
    }

    private String username;
    private String password;
    private String nome;
    private String cognome;
    private String email;
    private char sesso;
    private char nTel;
    private LocalDate dataNascita;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSesso(char sesso) {
        this.sesso = sesso;
    }

    public void setNTel(char nTel) {
        this.nTel = nTel;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }




    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public char getSesso() {
        return sesso;
    }

    public char getnTel() {
        return nTel;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }


}