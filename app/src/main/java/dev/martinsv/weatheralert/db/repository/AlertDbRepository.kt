package dev.martinsv.weatheralert.db.repository

import dev.martinsv.weatheralert.db.dao.AlertDao
import dev.martinsv.weatheralert.db.entity.AlertEntity

class AlertDbRepository(private val alertDao: AlertDao) {

    suspend fun addNewAlerts(cityId: Int, alerts: List<AlertEntity>) {
        alertDao.deleteAllAllertsByCityId(cityId)
        alertDao.insertAll(alerts = alerts)
    }

    fun alertsByCityId(cityId: Int) =
        alertDao.alertsByCityId(cityId)

    suspend fun deleteAlertsByCityId(cityId: Int) {
        alertDao.deleteAllAllertsByCityId(cityId)
    }

}