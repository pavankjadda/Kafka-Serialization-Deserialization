package com.kafkaserialization.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafkaserialization.model.Order;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class OrderDeserializer  implements Deserializer
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
     * Deserialize a record value from a byte array into a value or object.
     *
     * @param topic topic associated with the data
     * @param data  serialized bytes; may be null; implementations are recommended to handle null by returning a value or null rather than throwing an exception.
     * @return deserialized typed data; may be null
     */
    @Override
    public Object deserialize(String topic, byte[] data)
    {
        ObjectMapper objectMapper=new ObjectMapper();
        Order order=null;
        try
        {
            order=objectMapper.readValue(data,Order.class);
        }
        catch (Exception    e)
        {
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public void close()
    {

    }
}
