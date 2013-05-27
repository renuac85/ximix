/**
 * Copyright 2013 Crypto Workshop Pty Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.cryptoworkshop.ximix.common.message;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Enumerated;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;

public class Command
    extends ASN1Object
{
    private final Type type;
    private final ASN1Encodable payload;

    public static enum Type
    {
        UPLOAD_TO_BOARD
    }

    public Command(Type type, ASN1Encodable payload)
    {
        this.type = type;
        this.payload = payload;
    }

    private Command(ASN1Sequence seq)
    {
        this.type = Type.values()[ASN1Enumerated.getInstance(seq.getObjectAt(0)).getValue().intValue()];
        this.payload = seq.getObjectAt(1);
    }

    public static final Command getInstance(Object o)
    {
        if (o instanceof Command)
        {
            return (Command)o;
        }
        else if (o != null)
        {
            return new Command(ASN1Sequence.getInstance(o));
        }

        return null;
    }

    public Type getType()
    {
        return type;
    }

    public ASN1Encodable getPayload()
    {
        return payload;
    }

    @Override
    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();

        v.add(new ASN1Enumerated(type.ordinal()));
        v.add(payload);

        return new DERSequence(v);
    }
}