package org.example.cas;

import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class TestAtomicMarkableference {
    public static void main(String[] args) {

        GarbageBag garbageBag = new GarbageBag("FULL");

        AtomicMarkableReference<GarbageBag> ref = new AtomicMarkableReference<>(garbageBag,true);

        GarbageBag reference = ref.getReference();


        ref.compareAndSet(reference, new GarbageBag("empty"), true, false);
    }
}

class GarbageBag {
    String desc;

    public GarbageBag(String desc) {
        this.desc = desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "GarbageBag{" +
                "desc='" + desc + '\'' +
                '}';
    }
}
