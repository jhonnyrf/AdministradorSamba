
package proyectoaso;

import java.util.LinkedHashMap;
import java.util.Map;

public class SambaConfigSection {
    private String nombreSeccion;
    private Map<String, String> settings;

    public SambaConfigSection(String nombreSeccione) {
        this.nombreSeccion = nombreSeccione;
        this.settings = new LinkedHashMap<>();
    }

    public void addSetting(String key, String value) {
        settings.put(key, value);
    }

    public String getSectionName() {
        return nombreSeccion;
    }
    public void setSettings(Map<String, String> settings){
        this.settings=settings;
    }
    public Map<String, String> getSettings() {
        return settings;
    }

    @Override
    public String toString() {
        return "Section: " + nombreSeccion + " Settings: " + settings.toString();
    }
    
        // Constructor de copia profunda
    public SambaConfigSection(SambaConfigSection other) {
        this.nombreSeccion = other.nombreSeccion;
        this.settings = new LinkedHashMap<>(other.settings);
    }

}
