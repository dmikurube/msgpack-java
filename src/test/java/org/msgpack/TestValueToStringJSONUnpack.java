package org.msgpack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.msgpack.packer.Packer;
import org.msgpack.unpacker.Unpacker;
import org.msgpack.packer.Unconverter;
import org.msgpack.unpacker.Converter;
import org.msgpack.type.Value;
import org.msgpack.type.ValueFactory;
import org.msgpack.util.json.JSON;


public class TestValueToStringJSONUnpack extends TestSet {

    @Test @Override
    public void testBoolean() throws Exception {
	super.testBoolean();
    }

    @Override
    public void testBoolean(boolean v) throws Exception {
	MessagePack msgpack = new JSON();
        Unconverter packer = new Unconverter(msgpack);
	packer.writeBoolean(v);
        byte[] bytes = packer.getResult().toString().getBytes("UTF-8");
        Unpacker unpacker = msgpack.createBufferUnpacker(bytes);
	boolean ret = unpacker.readBoolean();
	assertEquals(v, ret);
    }

    @Test @Override
    public void testByte() throws Exception {
	super.testByte();
    }

    @Override
    public void testByte(byte v) throws Exception {
	MessagePack msgpack = new JSON();
        Unconverter packer = new Unconverter(msgpack);
	packer.writeByte(v);
        byte[] bytes = packer.getResult().toString().getBytes("UTF-8");
        Unpacker unpacker = msgpack.createBufferUnpacker(bytes);
	byte ret = unpacker.readByte();
	assertEquals(v, ret);
    }

    @Test @Override
    public void testShort() throws Exception {
	super.testShort();
    }

    @Override
    public void testShort(short v) throws Exception {
	MessagePack msgpack = new JSON();
        Unconverter packer = new Unconverter(msgpack);
	packer.writeShort(v);
        byte[] bytes = packer.getResult().toString().getBytes("UTF-8");
        Unpacker unpacker = msgpack.createBufferUnpacker(bytes);
	short ret = unpacker.readShort();
	assertEquals(v, ret);
    }

    @Test @Override
    public void testInteger() throws Exception {
	super.testInteger();
    }

    @Override
    public void testInteger(int v) throws Exception {
	MessagePack msgpack = new JSON();
        Unconverter packer = new Unconverter(msgpack);
	packer.writeInt(v);
        byte[] bytes = packer.getResult().toString().getBytes("UTF-8");
        Unpacker unpacker = msgpack.createBufferUnpacker(bytes);
	int ret = unpacker.readInt();
	assertEquals(v, ret);
    }

    @Test @Override
    public void testLong() throws Exception {
	super.testLong();
    }

    @Override
    public void testLong(long v) throws Exception {
	MessagePack msgpack = new JSON();
        Unconverter packer = new Unconverter(msgpack);
	packer.writeLong(v);
        byte[] bytes = packer.getResult().toString().getBytes("UTF-8");
        Unpacker unpacker = msgpack.createBufferUnpacker(bytes);
	long ret = unpacker.readLong();
	assertEquals(v, ret);
    }

    @Test @Override
    public void testFloat() throws Exception {
	super.testFloat();
    }

    @Override
    public void testFloat(float v) throws Exception {
	MessagePack msgpack = new JSON();
        Unconverter packer = new Unconverter(msgpack);
        if(((Float)v).isInfinite()) {
            packer.writeDouble(v);
            String str = packer.getResult().toString();
            if(v < 0) {
                assertEquals("-Infinity", str);
            } else {
                assertEquals("Infinity", str);
            }
            return;
        } else if(((Float)v).isNaN()) {
            packer.writeDouble(v);
            String str = packer.getResult().toString();
            assertEquals("NaN", str);
            return;
        }
	packer.writeFloat(v);
        byte[] bytes = packer.getResult().toString().getBytes("UTF-8");
        Unpacker unpacker = msgpack.createBufferUnpacker(bytes);
	float ret = unpacker.readFloat();
	assertEquals(v, ret, 10e-10);
    }

    @Test @Override
    public void testDouble() throws Exception {
	super.testDouble();
    }

    @Override
    public void testDouble(double v) throws Exception {
	MessagePack msgpack = new JSON();
        Unconverter packer = new Unconverter(msgpack);
        if(((Double)v).isInfinite()) {
            packer.writeDouble(v);
            String str = packer.getResult().toString();
            if(v < 0) {
                assertEquals("-Infinity", str);
            } else {
                assertEquals("Infinity", str);
            }
            return;
        } else if(((Double)v).isNaN()) {
            packer.writeDouble(v);
            String str = packer.getResult().toString();
            assertEquals("NaN", str);
            return;
        }
	packer.writeDouble(v);
        byte[] bytes = packer.getResult().toString().getBytes("UTF-8");
        Unpacker unpacker = msgpack.createBufferUnpacker(bytes);
	double ret = unpacker.readDouble();
	assertEquals(v, ret, 10e-10);
    }

    @Test @Override
    public void testNil() throws Exception {
	MessagePack msgpack = new JSON();
        Unconverter packer = new Unconverter(msgpack);
	packer.writeNil();
        byte[] bytes = packer.getResult().toString().getBytes("UTF-8");
        Unpacker unpacker = msgpack.createBufferUnpacker(bytes);
	unpacker.readNil();
    }

    //@Test @Override  // FIXME JSON Unpacker doesn't support BigInteger
    public void testBigInteger() throws Exception {
	super.testBigInteger();
    }

    @Override
    public void testBigInteger(BigInteger v) throws Exception {
	MessagePack msgpack = new JSON();
        Unconverter packer = new Unconverter(msgpack);
	packer.writeBigInteger(v);
        byte[] bytes = packer.getResult().toString().getBytes("UTF-8");
        Unpacker unpacker = msgpack.createBufferUnpacker(bytes);
	BigInteger ret = unpacker.readBigInteger();
	assertEquals(v, ret);
    }

    @Test @Override
    public void testString() throws Exception {
	super.testString();
    }

    @Override
    public void testString(String v) throws Exception {
	MessagePack msgpack = new JSON();
        Unconverter packer = new Unconverter(msgpack);
	packer.writeString(v);
        byte[] bytes = packer.getResult().toString().getBytes("UTF-8");
        Unpacker unpacker = msgpack.createBufferUnpacker(bytes);
	String ret = unpacker.readString();
	assertEquals(v, ret);
    }

    @Test @Override
    public void testByteArray() throws Exception {
	super.testByteArray();
    }

    @Override
    public void testByteArray(byte[] v) throws Exception {
        // FIXME JSONPacker doesn't support bytes
	MessagePack msgpack = new JSON();
        Unconverter packer = new Unconverter(msgpack);
	//packer.writeByteArray(v);
        String str = new String(v);
	packer.writeString(str);
        byte[] bytes = packer.getResult().toString().getBytes("UTF-8");
        Unpacker unpacker = msgpack.createBufferUnpacker(bytes);
	//byte[] ret = unpacker.readByteArray();
	String ret = unpacker.readString();
	assertEquals(str, ret);
    }

    @Test @Override
    public void testList() throws Exception {
	super.testList();
    }

    @Override
    public <E> void testList(List<E> v, Class<E> elementClass) throws Exception {
	MessagePack msgpack = new JSON();
        Unconverter packer = new Unconverter(msgpack);
	if (v == null) {
	    packer.writeNil();
	} else {
	    packer.writeArrayBegin(v.size());
	    for (Object o : v) {
		packer.write(o);
	    }
	    packer.writeArrayEnd();
	}
        byte[] bytes = packer.getResult().toString().getBytes("UTF-8");
        Unpacker unpacker = msgpack.createBufferUnpacker(bytes);
        if (unpacker.trySkipNil()) {
            assertEquals(null, v);
            return;
        }
	int size = unpacker.readArrayBegin();
	List ret = new ArrayList(size);
	for (int i = 0; i < size; ++i) {
	    ret.add(unpacker.read(elementClass));
	}
	unpacker.readArrayEnd();
	assertEquals(v.size(), ret.size());
	Iterator v_iter = v.iterator();
	Iterator ret_iter = ret.iterator();
	while (v_iter.hasNext()) {
	    assertEquals(v_iter.next(), ret_iter.next());
	}
    }

    @Test @Override
    public void testMap() throws Exception {
	super.testMap();
    }

    @Override
    public <K, V> void testMap(Map<K, V> v, Class<K> keyElementClass, Class<V> valueElementClass) throws Exception {
	MessagePack msgpack = new JSON();
        Unconverter packer = new Unconverter(msgpack);
	if (v == null) {
	    packer.writeNil();
	} else {
	    packer.writeMapBegin(v.size());
	    for (Map.Entry<Object, Object> e : ((Map<Object, Object>) v).entrySet()) {
		packer.write(e.getKey());
		packer.write(e.getValue());
	    }
	    packer.writeMapEnd();
	}
        byte[] bytes = packer.getResult().toString().getBytes("UTF-8");
        if(!keyElementClass.equals(String.class)) {
            // TODO JSONUnpacker rejects maps whose keys are not string
            return;
        }
        Unpacker unpacker = msgpack.createBufferUnpacker(bytes);
	if (unpacker.trySkipNil()) {
	    assertEquals(null, v);
	    return;
	}
	int size = unpacker.readMapBegin();
	Map ret = new HashMap(size);
	for (int i = 0; i < size; ++i) {
	    Object key = unpacker.read(keyElementClass);
	    Object value = unpacker.read(valueElementClass);
	    ret.put(key, value);
	}
	unpacker.readMapEnd();
	assertEquals(v.size(), ret.size());
	for (Map.Entry<Object, Object> e : ((Map<Object, Object>) v).entrySet()) {
	    Object value = ret.get(e.getKey());
	    assertEquals(e.getValue(), value);
	}
    }
}
