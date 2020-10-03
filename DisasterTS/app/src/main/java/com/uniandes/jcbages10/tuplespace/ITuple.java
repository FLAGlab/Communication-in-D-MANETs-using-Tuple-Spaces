package com.uniandes.jcbages10.tuplespace;

import java.util.Optional;
import java.util.UUID;

public interface ITuple {

    UUID owner();

    Optional<ITuple> match(ITuple tuple);

    IField get(int position);

    int length();

    long leasing();

}
