package usuario.app.rpg_cl_project.domain;

import java.util.ArrayList;
import java.util.List;

public class AppSetting {
    private List<ConfiguracaoGeral> configuracoesGerais;

    public AppSetting(){
        this.configuracoesGerais = new ArrayList<ConfiguracaoGeral>();
    }

    public List<ConfiguracaoGeral> getConfiguracoesGerais() {
        return this.configuracoesGerais;
    }

    public void setConfiguracoesGerais(List<ConfiguracaoGeral> configuracoesGerais) {
        this.configuracoesGerais = configuracoesGerais;
    }
}
