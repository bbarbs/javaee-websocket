package com.ejb.app.session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ejb.app.entity.Equipment;

/**
 * Session Bean implementation class EquipmentBean
 */
@Stateless
public class EquipmentBean implements EquipmentBeanLocal {
   	
	 @PersistenceContext(unitName="pcc4_database")
		private EntityManager em;
		   
		/** Static variable for checking initialization by Web context listener. */
		private static boolean initialized = false;
		
		@Override
		public void persistData() {					
			Equipment equip = new Equipment();
			equip.equipName = "CAT47";
			equip.license = "56OP";
			equip.state = "Stop_Empty";
			equip.efficiency = 19;
			equip.activity = "Maintenance";
			equip.operator = "logimine";
			em.persist(equip);
			em.flush();
		}

		@Override
		public void init() {
			initialized = true;	
		}

		@Override
		public boolean isInit() {
			return initialized;
		}

		@Override
		public String hello() {
			return "Hello";
		}
		
		@Override
		public List<?> getAllList() {		
			Query q = (Query) em.createNativeQuery("SELECT eq.pk, eq.equipName, eq.license, eq.state, eq.efficiency, " +
					"eq.activity, eq.operator " +
					"FROM Equipment eq ", Equipment.class);
			
			return q.getResultList();
		}

		@Override
		public List<?> getEquipByPk(int pk) {
			Query q = (Query) em.createNativeQuery("SELECT eq.pk, eq.equipName, eq.license, eq.state, eq.efficiency, " +
					"eq.activity, eq.operator " +
					"FROM Equipment eq " +
					"WHERE eq.pk=:pk ", Equipment.class)
					.setParameter("pk", pk);
			
			return q.getResultList();
		}
		
		@Override
		public List<?> getEquipByName(String equipName) {
			Query q = (Query) em.createNativeQuery("SELECT eq.pk, eq.equipName, eq.license, eq.state, eq.efficiency, " +
					"eq.activity, eq.operator " +
					"FROM Equipment eq " +
					"WHERE eq.equipName=:name ", Equipment.class)
					.setParameter("name", equipName);
			
			return q.getResultList();
		}

		@Override
		public List<?> getActivityAndOperator(String activity, String operator) {
			Query q = (Query) em.createNativeQuery("SELECT eq.pk, eq.equipName, eq.license, eq.state, eq.efficiency, " +
					"eq.activity, eq.operator " +
					"FROM Equipment eq " +
					"WHERE eq.activity=:activity " +
					"AND eq.operator=:operator ", Equipment.class)
					.setParameter("activity", activity)
					.setParameter("operator", operator);
			
			return q.getResultList();
		}

		@Override
		public int insertEntity(String equipName, String license, String state, int efficiency,
				 String activity, String operator) {
			try {
				Equipment equip = new Equipment();
				equip.equipName = equipName;
				equip.license = license;
				equip.state = state;
				equip.efficiency = efficiency;
				equip.activity = activity;
				equip.operator = operator;
				em.persist(equip);
				em.flush();
			} catch (Exception e) {
				e.printStackTrace();
				// Return http code server error(internal server error)
				return 500;
			}
			// Return http code success(ok).
			return 200;
		}

		@Override
		public int deleteState(int pk) {
			try {
				Equipment equip = em.find(Equipment.class, pk);
				em.remove(equip);
				em.flush();
			} catch (Exception e) {
				e.printStackTrace();
				// Return http code server error(internal server error)
				return 500;
			}		
			// Return http code success(ok).
			return 200;
		}

		@Override
		public int updateEquip(int pk, String equipName, String license, String state, int efficiency, String activity, String operator) {
			try {
				Equipment equip = em.find(Equipment.class, pk);
				equip.setEquipName(equipName);
				equip.setLicense(license);
				equip.setState(state);	
				equip.setEfficiency(efficiency);
				equip.setActivity(activity);
				equip.setOperator(operator);
			} catch (Exception e) {
				e.printStackTrace();
				// Return http code server error(internal server error)
				return 500;
			}		
			// Return http code success(ok).
			return 200;
		}

}
