package usuario.app.rpg_cl_project.domain;

import java.util.ArrayList;
import java.util.List;

public class ConfiguracaoApp {
    private List<ConfiguracaoGeral> configuracoesGerais;

    public ConfiguracaoApp(){
        this.configuracoesGerais = new ArrayList<ConfiguracaoGeral>();
    }

    public List<ConfiguracaoGeral> getConfiguracoesGerais() {
        return this.configuracoesGerais;
    }

    public void setConfiguracoesGerais(List<ConfiguracaoGeral> configuracoesGerais) {
        this.configuracoesGerais = configuracoesGerais;
    }
}
