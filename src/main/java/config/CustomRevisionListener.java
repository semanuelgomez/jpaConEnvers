package config;

import audit.Revision;
import org.hibernate.envers.RevisionListener;

import java.io.Serializable;

public class CustomRevisionListener implements RevisionListener {
    //@Override
    public void newRevision(Object revisionEntity) {
        // TODO Auto-generated method stub

        //Revision a= (Revision)revisionEntity;
        final Revision revision = (Revision) revisionEntity;

    }
}
