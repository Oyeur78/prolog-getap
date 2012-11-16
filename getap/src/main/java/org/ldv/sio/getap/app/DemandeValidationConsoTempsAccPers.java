package org.ldv.sio.getap.app;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demande de validation d'un temps d'accompagnement personnalisé
 * 
 * 
 */

public class DemandeValidationConsoTempsAccPers {
	/**
	 * Les etats
	 */
	private static final int CREER_ELEVE = 0;
	private static final int ACCEPTER_ELEVE_MOD_PROF = 1;
	private static final int REJETEE_ELEVE_MOD_PROF = 2;
	private static final int MODIFIEE_ELEVE = 4;
	private static final int REJETEE_ELEVE = 8;
	private static final int VALIDER_PROF = 32;
	private static final int REJETEE_PROF = 64;
	private static final int DATE_MODIFIEE = 1024;
	private static final int DUREE_MODIFIEE = 2048;
	private static final int AP_MODIFIEE = 4096;

	/**
	 * Identifiant de la DCTAP
	 */
	private Long id;
	/**
	 * Année scolaire de la demande, par exemple "2011-2012"
	 */
	private String anneeScolaire;
	/**
	 * Date de réalisation de l'accompagnement
	 * 
	 */
	private java.sql.Date dateAction;
	/**
	 * Nombre de minutes d'accompagnement personnalisé à valider
	 */
	private Integer minutes;
	/**
	 * Professeur ayant assuré l'accompagnement personnalisé
	 */
	private User prof;
	/**
	 * Nature de l'accompagnement personnalisé associé à la demande
	 */
	private AccPersonalise accPers;
	/**
	 * Identifiant de l'élève ayant réalisé l'accompagnement personnalisé
	 */
	private User eleve;

	/**
	 * 
	 */
	private int etat;

	/**
	 * constructeur par défaut
	 */
	private final Logger logger = LoggerFactory
			.getLogger(DemandeValidationConsoTempsAccPers.class);

	public DemandeValidationConsoTempsAccPers() {

	}

	/**
	 * Constructeur permettant de créer une demande complète.
	 * 
	 * @param id
	 *            peut être null (création)
	 * @param anneeScolaire
	 * @param date
	 * @param minutes
	 * @param prof
	 * @param accPers
	 * @param eleve
	 * @param etat
	 */
	public DemandeValidationConsoTempsAccPers(Long id, String anneeScolaire,
			Date date, Integer minutes, User prof, AccPersonalise accPers,
			User eleve, int etat) {
		super();
		this.id = id;
		this.anneeScolaire = anneeScolaire;
		this.dateAction = date;
		this.minutes = minutes;
		this.prof = prof;
		this.accPers = accPers;
		this.eleve = eleve;
		this.etat = etat;
	}

	/**
	 * Constructeur permettant de créer une demande complète avec un état
	 * intialiser à 0.
	 */
	public DemandeValidationConsoTempsAccPers(Long id, String anneeScolaire,
			Date date, Integer minutes, User prof, AccPersonalise accPers,
			User eleve) {
		this(id, anneeScolaire, date, minutes, prof, accPers, eleve, 0);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAnneeScolaire() {
		return anneeScolaire;
	}

	public void setAnneeScolaire(String anneeScolaire) {
		this.anneeScolaire = anneeScolaire;
	}

	public java.sql.Date getDateAction() {
		return dateAction;
	}

	public void setDateAction(java.sql.Date date) {
		this.dateAction = date;
	}

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	public User getProf() {
		return prof;
	}

	public void setProf(User prof) {
		this.prof = prof;
	}

	public AccPersonalise getAccPers() {
		return accPers;
	}

	public void setAccPers(AccPersonalise accPers) {
		this.accPers = accPers;
	}

	public User getEleve() {
		return eleve;
	}

	public void setEleve(User eleve) {
		this.eleve = eleve;
	}

	public int getEtat() {
		return etat;
	}

	/**
	 * Permet de modifier l'état de la demande
	 * 
	 * @param etat
	 *            prend ses valeur dans :
	 *            <ul>
	 *            <li>0 - demande créée par l'élève</li>
	 *            <li>1 - demande acceptée par l'élève aprés modification du
	 *            professeur</li>
	 *            <li>2 - demande rejetée par l'élève aprés modification du
	 *            professeur</li>
	 *            <li>4 - demande modifiée par l'élève</li>
	 *            <li>8 - demande annulée par l'élève</li>
	 *            <li>32 - demande validée par le professeur</li>
	 *            <li>64 - demande refusée par le professeur</li>
	 *            <li>1024 - demande où la date a été modifiée par le professeur
	 *            </li>
	 *            <li>2048 - demande où la durée a été modifiée par le
	 *            professeur</li>
	 *            <li>4096 - demande où l'accompagnement personnalisé a été
	 *            modifiée par le professeur</li>
	 *            </ul>
	 */
	public void setEtat(int etat) {
		this.etat = etat;
	}

	public boolean isEtatInitial() {
		if (etat == CREER_ELEVE) {
			return true;
		}
		return false;
	}

	public boolean isAccepterParEleveApresModif() {
		return (this.etat & ACCEPTER_ELEVE_MOD_PROF) != 0;
	}

	public boolean isRejeterEleveApresModif() {
		return (this.etat & REJETEE_ELEVE_MOD_PROF) != 0;
	}

	public boolean isModifierEleve() {
		return (this.etat & MODIFIEE_ELEVE) != 0;
	}

	public boolean isRejeteeEleve() {
		return (this.etat & REJETEE_ELEVE) != 0;
	}

	public boolean isValiderProf() {
		return (this.etat & VALIDER_PROF) != 0;
	}

	public boolean isRefuserProf() {
		return (this.etat & REJETEE_PROF) != 0;
	}

	public boolean isDateModifiee() {
		return (this.etat & DATE_MODIFIEE) != 0;
	}

	public boolean isDureeModifiee() {
		return (this.etat & DUREE_MODIFIEE) != 0;
	}

	public boolean isApModifiee() {
		return (this.etat & AP_MODIFIEE) != 0;
	}

	public void setDtapInitial() {
		this.etat = 0;
	}

	public void setDctapConfirme() {
		this.etat = 1;
	}

	public void setDctapRejete() {
		this.etat = 2;
	}

	public void setDctapModifEleve() {
		this.etat = 4;
	}

	public void setDctapAnnule() {
		this.etat = 8;
	}

	public void setDctapValide() {
		this.etat = 32;
	}

	public void setDctapRefuse() {
		this.etat = 64;
	}

	public void setDctapDateModif() {
		this.etat = this.getEtat() + 1024;
	}

	public void setDctapDureeModif() {
		this.etat = this.getEtat() + 2048;
	}

	public void setDctapAccModif() {
		this.etat = this.getEtat() + 4096;
	}

	public void accepteeParEleve() throws DVCTAPException {
		if (!this.isValiderProf()
				&& !this.isRefuserProf()
				&& !this.isRejeterEleveApresModif()
				&& !this.isRejeteeEleve()
				&& !this.isValiderProf()
				&& !this.isAccepterParEleveApresModif()
				&& (this.isApModifiee() || this.isDateModifiee() || this
						.isDureeModifiee())) {
			this.etat = this.etat | ACCEPTER_ELEVE_MOD_PROF;
			logger.info("Accepté par l'élève: " + this.toString());
		} else {
			throw new DVCTAPException(
					"La demande ne peux être accepté par l'élève !");
		}
	}

	public void rejeteParEleveApresModProf() throws DVCTAPException {
		if (!this.isValiderProf()
				&& !this.isRefuserProf()
				&& !this.isAccepterParEleveApresModif()
				&& !this.isRejeteeEleve()
				&& !this.isValiderProf()
				&& !this.isRejeterEleveApresModif()
				&& (this.isApModifiee() || this.isDateModifiee() || this
						.isDureeModifiee())) {
			this.etat = this.etat | REJETEE_ELEVE_MOD_PROF;
			logger.info("Rejeté par l'élève après modification: "
					+ this.toString());
		} else {
			throw new DVCTAPException(
					"La demande ne peux être rejeté par l'élève !");
		}
	}

	public void modifieeParEleve() throws DVCTAPException {
		if (!this.isValiderProf() && !this.isRefuserProf()
				&& !this.isAccepterParEleveApresModif()
				&& !this.isRejeterEleveApresModif() && !this.isApModifiee()
				&& !this.isDureeModifiee() && !this.isDateModifiee()
				&& !this.isRejeteeEleve()) {
			this.etat = this.etat | MODIFIEE_ELEVE;
			logger.info("Modifié par l'élève " + this.toString());
		} else {
			throw new DVCTAPException(
					"La demande ne peux être modifier par l'élève !");
		}
	}

	public void rejeteeParEleve() throws DVCTAPException {
		if (!this.isValiderProf() && !this.isRefuserProf()
				&& !this.isAccepterParEleveApresModif()
				&& !this.isRejeterEleveApresModif() && !this.isApModifiee()
				&& !this.isDureeModifiee() && !this.isDateModifiee()
				&& !this.isRejeteeEleve()) {
			this.etat = this.etat | REJETEE_ELEVE;
			logger.info("Annuler par l'élève: " + this.toString());
		} else {
			throw new DVCTAPException(
					"La demande ne peut être annuler par l'élève !");
		}
	}

	public void valideeParLeProfesseur() throws DVCTAPException {
		if (!this.isRejeteeEleve() && !this.isRefuserProf()
				&& !this.isAccepterParEleveApresModif()
				&& !this.isRejeterEleveApresModif() && !this.isValiderProf()) {
			this.etat = this.etat | VALIDER_PROF;
			logger.info("Validé par le professeur: " + this.toString());
		} else {
			throw new DVCTAPException(
					"La demande ne peux être valider par le professeur !");
		}
	}

	public void rejeteeParLeProfesseur() throws DVCTAPException {
		if (!this.isRejeteeEleve() && !this.isValiderProf()
				&& !this.isAccepterParEleveApresModif()
				&& !this.isRejeterEleveApresModif() && !this.isRefuserProf()) {
			this.etat = this.etat | REJETEE_PROF;
			logger.info("Rejeté par le professeur: " + this.toString());
		} else {
			throw new DVCTAPException(
					"La demande ne peux être refusé par le professeur !");
		}
	}

	public void dureeModifieeParLeProfesseur() throws DVCTAPException {
		if (!this.isValiderProf() && !this.isRefuserProf()
				&& !this.isAccepterParEleveApresModif()
				&& !this.isRejeterEleveApresModif() && !this.isRejeteeEleve()
				&& !this.isRefuserProf()) {
			this.etat = this.etat | DUREE_MODIFIEE;
			logger.info("Durée modifié par le professeur: " + this.toString());
		} else {
			throw new DVCTAPException(
					"La demande ne peux être modifié par le professeur !");
		}
	}

	public void dateModifieeParLeProfesseur() throws DVCTAPException {
		if (!this.isValiderProf() && !this.isRefuserProf()
				&& !this.isAccepterParEleveApresModif()
				&& !this.isRejeterEleveApresModif() && !this.isRejeteeEleve()
				&& !this.isRefuserProf()) {
			this.etat = this.etat | DATE_MODIFIEE;
			logger.info("Date modifié par le professeur: " + this.toString());
		} else {
			throw new DVCTAPException(
					"La demande ne peux être modifié par le professeur !");
		}
	}

	public void modifieeParLeProfesseur() throws DVCTAPException {
		if (!this.isValiderProf() && !this.isRefuserProf()
				&& !this.isAccepterParEleveApresModif()
				&& !this.isRejeterEleveApresModif() && !this.isRejeteeEleve()
				&& !this.isRefuserProf()) {
			this.etat = this.etat | AP_MODIFIEE;
			logger.info("Accompagnement personalisé modifié par le professeur: "
					+ this.toString());
		} else {
			throw new DVCTAPException(
					"La demande ne peux être modifié par le professeur !");
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((anneeScolaire == null) ? 0 : anneeScolaire.hashCode());
		result = prime * result
				+ ((dateAction == null) ? 0 : dateAction.hashCode());
		result = prime * result + ((eleve == null) ? 0 : eleve.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DemandeValidationConsoTempsAccPers other = (DemandeValidationConsoTempsAccPers) obj;
		if (anneeScolaire == null) {
			if (other.anneeScolaire != null)
				return false;
		} else if (!anneeScolaire.equals(other.anneeScolaire))
			return false;
		if (dateAction == null) {
			if (other.dateAction != null)
				return false;
		} else if (!dateAction.equals(other.dateAction))
			return false;
		if (eleve == null) {
			if (eleve != null)
				return false;
		} else if (!eleve.equals(other.eleve))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DemandeConsoTempsAccPers [id=" + id + ", anneeScolaire="
				+ anneeScolaire + ", dateAction=" + dateAction + ", minutes="
				+ minutes + ", prof=" + prof + ", accPers=" + accPers
				+ ", eleve=" + eleve + ", etat=" + etat + "]";
	}

}
