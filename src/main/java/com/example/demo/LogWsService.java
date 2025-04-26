package com.example.demo;

import com.example.demo.entity.Log;
import com.example.demo.entity.LogLevel;
import com.example.demo.entity.Logger;
import com.example.demo.entity.Params;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class LogWsService {

    private HttpServletRequest request;
    private EntityManager em;

    public LogWsService(EntityManager em, HttpServletRequest request) {
        this.request = request;
        this.em = em;
    }

    public void createLog(LogWsJson logWsJson) throws Exception {
        String loggerName = logWsJson.getLoggerName();
        String level = logWsJson.getLevel();

        Logger logger = findLogger(loggerName, level);

        if (logger == null) {
            throw new Exception("Настройка не найдена");
        }

        if (!logger.getLog()) {
            return;
        }

        Log log = new Log();
        log.setUrl(logWsJson.getUrl());
        log.setAddInfo(logWsJson.getAddInfo());
        log.setStack(logWsJson.getStackTrace());
        log.setMessage(logWsJson.getMessage());
        log.setIp(getClientIp(request));
        log.setLogger(logger);

        Params params = new Params();
        params.setParams(logWsJson.getParams());
        params.setLog(log);

        log.setParams(params);

        em.persist(log);

    }

    private Logger findLogger(String loggerName, String level) {
        LogLevel levelEnum = LogLevel.valueOf(level);
//
//
//        String jpql = "select l from Logger l where l.loggerName = :loggerName and l.level = CAST(:levelEnum AS log_level)";
//
//        TypedQuery<Logger> query = em.createQuery(jpql, Logger.class);
//        query.setParameter("loggerName", loggerName);
//        query.setParameter("levelEnum", levelEnum);
//        Logger logger = query.getSingleResult();
//
//        return logger;

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Logger> cq = cb.createQuery(Logger.class);
        Root<Logger> root = cq.from(Logger.class);

        Predicate predicate = cb.and(
                cb.equal(root.get("loggerName"), loggerName),
                cb.equal(root.get("level"), levelEnum)
        );

        cq.select(root).where(predicate);

        List<Logger> results = em.createQuery(cq).setMaxResults(1).getResultList();
        Logger logger = results.isEmpty() ? null : results.get(0);

        return logger;
    }

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
            return ip.split(",")[0].trim(); // берём первый IP
        }
        return request.getRemoteAddr();
    }
}
