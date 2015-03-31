package br.com.grupopibb.portalrh.business;

import java.util.Date;
import java.util.Timer;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author administrator
 */
@Stateless
@LocalBean
public class MonitorScheduler {

    private Timer timer;

    public void init(MonitorTask monitorTask) {
        timer = new Timer();
        timer.schedule(monitorTask, new Date(), 1000l * 30l);
    }
}

