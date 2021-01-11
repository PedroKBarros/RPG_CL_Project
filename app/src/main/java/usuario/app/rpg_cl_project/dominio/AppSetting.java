package usuario.app.rpg_cl_project.dominio;

import java.util.ArrayList;
import java.util.List;

public class AppSetting {
    private List<GeneralSetting> generalSettings;

    public AppSetting(){
        this.generalSettings = new ArrayList<GeneralSetting>();
    }

    public List<GeneralSetting> getGeneralSettings() {
        return generalSettings;
    }

    public void setGeneralSettings(List<GeneralSetting> generalSettings) {
        this.generalSettings = generalSettings;
    }
}
