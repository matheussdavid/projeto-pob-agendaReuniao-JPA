package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programa��o Orientada a Objetos
 * Prof. Fausto Maranh�o Ayres
 **********************************/

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Participante {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	private String nome; 
	private String email;
	
	@ManyToMany(cascade= CascadeType.ALL,
				fetch= FetchType.LAZY)
	private List <Reuniao> reunioes = new ArrayList <Reuniao> ();

	public Participante(String nome, String email) 	{
		super();
		this.nome = nome;
		this.email = email;
	}
	
	public Participante() {}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Reuniao> getReunioes() 	{
		return reunioes;
	}

	public void adicionar(Reuniao r)	{
		reunioes.add(r);
	}

	public void remover(Reuniao r)	{
		reunioes.remove(r);
	}

	public int getTotalReunioes() 	{
		return reunioes.size();
	}

	@Override
	public String toString() 	{
		String texto = "Nome: " + nome + ", email: " + email + ", Reuni�es: ";
		
		if (reunioes.isEmpty())
			texto += " sem reuni�o";
		else 	
			for(Reuniao r : reunioes) 	
				texto += " " + r.getId();

		return texto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

