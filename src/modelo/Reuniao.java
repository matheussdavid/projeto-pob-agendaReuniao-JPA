package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programação Orientada a Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Reuniao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime datahora;

	private String assunto;
	
	@ManyToMany(mappedBy="reunioes", 
				cascade= CascadeType.ALL,
				fetch= FetchType.LAZY)
	private List <Participante> participantes = new ArrayList <Participante>();

	public Reuniao(LocalDateTime datahora, String assunto) 	{
		this.datahora = datahora;
		this.assunto = assunto;
	}
	
	public Reuniao() {}

	public void adicionar(Participante p)	{
		participantes.add(p);
	}

	public void remover(Participante p)	{
		participantes.remove(p);
	}

	public Participante localizarParticipante(String nome)	{
		for(Participante p: participantes)	{
			if(p.getNome().equals(nome))
				return p;
		}
		return null;
	}

	public List<Participante> getParticipantes() 	{
		return participantes;
	}

	public void setParticipantes(ArrayList<Participante> participantes) 	{
		this.participantes = participantes;
	}

	public int getTotalParticipantes()	{
		return participantes.size();
	}

	public int getId() 	{
		return id;
	}

	public void setId(int id) 	{
		this.id = id;
	}

	public LocalDateTime getDatahora() 	{
		return this.datahora;
	}

	public void setDatahora(LocalDateTime dth) 	{
		this.datahora = dth;
	}

	public String getAssunto() 	{
		return assunto;
	}

	public void setAssunto(String assunto) 	{
		this.assunto = assunto;
	}

	@Override
	public String toString() 	{
		String texto = "id: " + id + ", Horário: " + datahora + ", Assunto: " + assunto;

		texto +=  "\n Participantes:";
		for(Participante p: participantes) 
			texto += " " + p.getNome();

		return texto ;
	}
}





