/*
 * This file is generated by jOOQ.
 */
package me.vmorozov.orm.playground.jooq.generated;

import org.jooq.Sequence;
import org.jooq.impl.SequenceImpl;

import javax.annotation.Generated;


/**
 * Convenience access to all sequences in public
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Sequences {

    /**
     * The sequence <code>public.company_id_seq</code>
     */
    public static final Sequence<Integer> COMPANY_ID_SEQ = new SequenceImpl<Integer>("company_id_seq", Public.PUBLIC, org.jooq.impl.SQLDataType.INTEGER.nullable(false));

    /**
     * The sequence <code>public.department_id_seq</code>
     */
    public static final Sequence<Integer> DEPARTMENT_ID_SEQ = new SequenceImpl<Integer>("department_id_seq", Public.PUBLIC, org.jooq.impl.SQLDataType.INTEGER.nullable(false));

    /**
     * The sequence <code>public.employee_id_seq</code>
     */
    public static final Sequence<Integer> EMPLOYEE_ID_SEQ = new SequenceImpl<Integer>("employee_id_seq", Public.PUBLIC, org.jooq.impl.SQLDataType.INTEGER.nullable(false));
}