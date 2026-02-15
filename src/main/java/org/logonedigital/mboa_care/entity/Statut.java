package org.logonedigital.mboa_care.entity;

import ch.qos.logback.core.status.Status;

import java.util.Iterator;

// C’est un type spécial qui limite les valeurs possibles d’une variable à un ensemble prédéfini.
//
//On l’utilise quand tu veux qu’une variable ne puisse prendre qu’une liste de valeurs fixes.

public enum Statut implements Status {
    ACCEPTEE,
    EN_ATTENTE,
    REFUSEE,
    TERMINEE,
    EN_COURS;

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getEffectiveLevel() {
        return 0;
    }

    @Override
    public Object getOrigin() {
        return null;
    }

    @Override
    public String getMessage() {
        return "";
    }

    @Override
    public Throwable getThrowable() {
        return null;
    }

    @Override
    public long getTimestamp() {
        return 0;
    }

    @Override
    public boolean hasChildren() {
        return false;
    }

    @Override
    public void add(Status status) {

    }

    @Override
    public boolean remove(Status status) {
        return false;
    }

    @Override
    public Iterator<Status> iterator() {
        return null;
    }
}
