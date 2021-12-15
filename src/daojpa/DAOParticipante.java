package daojpa;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import modelo.Participante;

public class DAOParticipante extends DAO<Participante> {

	@Override
	public Participante read(Object chave) {
		try {
			String nome = (String) chave;
			TypedQuery<Participante> q = manager.createQuery("Select p from Participante p where p.nome=:n", Participante.class);
			q.setParameter("n", nome);
			return q.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}
	
	@Override
	public List<Participante> readAll(){
		TypedQuery<Participante> q = manager.createQuery("Select p from Participante p order by p.id", Participante.class);
		return q.getResultList();
	}

	public List<Participante> encontrarParticipantes(String nome, int mes) {
		try {
			TypedQuery<Participante> q = manager.createQuery ("SELECT p FROM Participante p WHERE p.nome LIKE :nome AND p.Reuniao.datahora LIKE :mes",Participante.class);
			q.setParameter("nome", "N%");
			q.setParameter("mes", "M%");
			return q.getResultList();
		} catch(Exception e) {
			return null;
		}
		
	}

}


