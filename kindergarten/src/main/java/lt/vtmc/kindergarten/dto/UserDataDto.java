package lt.vtmc.kindergarten.dto;

public class UserDataDto {

private String prisijungimo_vardas;
    private String vardas;
    private String pavardė;
    private String asmens_kodas;
    private String adresas;
    private String miestas;
    private String pašto_kodas;
    private String telefono_numeris;
    private String el_paštas;

    public UserDataDto() {
        this.prisijungimo_vardas = "";
        this.vardas = "";
        this.pavardė = "";
        this.asmens_kodas = "";
        this.adresas = "";
        this.miestas = "";
        this.pašto_kodas = "";
        this.telefono_numeris = "";
        this.el_paštas = "";
    }

    public String getPrisijungimo_vardas() {
        return prisijungimo_vardas;
    }

    public void setPrisijungimo_vardas(String prisijungimo_vardas) {
        this.prisijungimo_vardas = prisijungimo_vardas;
    }

    public String getVardas() {
        return vardas;
    }

    public void setVardas(String vardas) {
        this.vardas = vardas;
    }

    public String getPavardė() {
        return pavardė;
    }

    public void setPavardė(String pavardė) {
        this.pavardė = pavardė;
    }

    public String getAsmens_kodas() {
        return asmens_kodas;
    }

    public void setAsmens_kodas(String asmens_kodas) {
        this.asmens_kodas = asmens_kodas;
    }

    public String getAdresas() {
        return adresas;
    }

    public void setAdresas(String adresas) {
        this.adresas = adresas;
    }

    public String getMiestas() {
        return miestas;
    }

    public void setMiestas(String miestas) {
        this.miestas = miestas;
    }

    public String getPašto_kodas() {
        return pašto_kodas;
    }

    public void setPašto_kodas(String pašto_kodas) {
        this.pašto_kodas = pašto_kodas;
    }

    public String getTelefono_numeris() {
        return telefono_numeris;
    }

    public void setTelefono_numeris(String telefono_numeris) {
        this.telefono_numeris = telefono_numeris;
    }

    public String getEl_paštas() {
        return el_paštas;
    }

    public void setEl_paštas(String el_paštas) {
        this.el_paštas = el_paštas;
    }
}
