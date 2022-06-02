package bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;

import DAO.JogoDAO;
import entity.Jogo;

@ManagedBean
public class JogoBean {
	private Jogo jogo = new Jogo();
	private List<Jogo> lista;

	public String salvar() {
		try {
			JogoDAO.salvar(jogo);
			jogo = new Jogo();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Muito bom!", "Seu jogo foi salvo com sucesso!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Vixe",
					"Infelizmente seu jogo não foi salvo. Tente novamente daqui a pouquinho"));
		}

		return null;
	}

	public String editar(int id) {

		Jogo posicao = null;

		for (int i = 0; i < lista.size(); i++) {
			if (id == lista.get(i).getId()) {
				posicao = lista.get(i);
			}
		}

		try {
			JogoDAO.editar(posicao);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Parabéns!", "Seu jogo foi editado."));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro",
					"Você não conseguiu editar este jogo. Tente novamente depois"));
		}
		return null;
	}

	public String excluir() {
		try {
			JogoDAO.excluir(jogo);
			lista.remove(jogo);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "O jogo foi apagado com sucesso!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro", "O jogo não foi apagado."));
		}
		return null;
	}

	public void onRowCancel(RowEditEvent<Jogo> event) {
		FacesMessage msg = new FacesMessage("Edição cancelada", String.valueOf(event.getObject().getDescricao()));
		FacesContext.getCurrentInstance().addMessage("", msg);
	}

	public void filtrarPares(int id) {

		String exibir = "";
		Jogo posicao = null;

		for (int i = 0; i < lista.size(); i++) {
			if (id == lista.get(i).getId()) {
				posicao = lista.get(i);
			}
		}

		if ((posicao.getV1()) % 2 == 0) {
			exibir = "\n" + posicao.getV1() + "\n";
		}
		if ((posicao.getV2()) % 2 == 0) {
			exibir += posicao.getV2() + "\n";
		}
		if ((posicao.getV3()) % 2 == 0) {
			exibir += posicao.getV3() + "\n";
		}
		if ((posicao.getV4()) % 2 == 0) {
			exibir += posicao.getV4() + "\n";
		}
		if ((posicao.getV5()) % 2 == 0) {
			exibir += posicao.getV5() + "\n";
		}
		if ((posicao.getV6()) % 2 == 0) {
			exibir += posicao.getV6() + "\n";
		}
		if ((posicao.getV7()) % 2 == 0) {
			exibir += posicao.getV7() + "\n";
		}
		if ((posicao.getV8()) % 2 == 0) {
			exibir += posicao.getV8() + "\n";
		}
		if ((posicao.getV9()) % 2 == 0) {
			exibir += posicao.getV9() + "\n";
		}
		if ((posicao.getV10()) % 2 == 0) {
			exibir += posicao.getV10() + "";
		}
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Números pares:", exibir.toString());
		PrimeFaces.current().dialog().showMessageDynamic(message);

	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public List<Jogo> getLista() {
		if (lista == null)
			lista = JogoDAO.listar();
		return lista;
	}

	public void setLista(List<Jogo> lista) {
		this.lista = lista;
	}

}
