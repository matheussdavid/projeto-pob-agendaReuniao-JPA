package daojpa;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import modelo.Participante;
import modelo.Reuniao;

public class DAOReuniao extends DAO<Reuniao> {

	@Override
	public Reuniao read(Object chave) {
		try {
			int id = (int) chave;
			TypedQuery<Reuniao> q = manager.createQuery("Select r from Reuniao r where r.id=:id", Reuniao.class);
			q.setParameter("id", id);
			return q.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}	
	}
	
	@Override
	public List<Reuniao> readAll(){
		TypedQuery<Reuniao> q = manager.createQuery("Select r from Reuniao r order by r.id", Reuniao.class);
		return q.getResultList();
	}

	public List<Reuniao> reuniaoContemConvidado() {
		try {
			TypedQuery<Reuniao> q = manager.createQuery ("SELECT r FROM Reuniao r JOIN r.Convidado c  WHERE c.nome is not empty",Reuniao.class);
			return q.getResultList();
		} catch(Exception e){
			return null;
		}
	}

}


