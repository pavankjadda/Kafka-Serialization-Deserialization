package com.kafkaserialization.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class OrderSerializer    implements Serializer
{
    /**
     * Configure this class.
     *
     * @param configs configs in key/value pairs
     * @param isKey   whether is for key or value
     */
    @Override
    public void configure(Map configs, boolean isKey)
    {

    }

    /**
     * Convert {@code data} into a byte array.
     *
     * @param topic topic associated with data
     * @param data  typed data
     * @return serialized bytes
     */
    @Override
    public byte[] serialize(String topic, Object data)
    {
        byte[] serializedObject = null;
        try
        {
            ObjectMapper    objectMapper=new ObjectMapper();
            serializedObject=objectMapper.writeValueAsString(data).getBytes();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return serializedObject;
    }

    /**
     * Close this serializer.
     * <p>
     * This method must be idempotent as it may be called multiple times.
     */
    @Override
    public void close()
    {

    }
}
