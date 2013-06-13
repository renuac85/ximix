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
package org.cryptoworkshop.ximix.crypto.service;

import org.bouncycastle.asn1.ASN1Encodable;
import org.cryptoworkshop.ximix.common.conf.Config;
import org.cryptoworkshop.ximix.common.message.Capability;
import org.cryptoworkshop.ximix.common.message.ClientMessage;
import org.cryptoworkshop.ximix.common.message.Message;
import org.cryptoworkshop.ximix.common.message.MessageReply;
import org.cryptoworkshop.ximix.common.service.NodeContext;
import org.cryptoworkshop.ximix.common.service.Service;

public class NodeKeyGenerationService
    implements Service
{
    private final NodeContext nodeContext;

    public NodeKeyGenerationService(NodeContext nodeContext, Config config)
    {
        this.nodeContext = nodeContext;
    }

    public Capability getCapability()
    {
        return new Capability(Capability.Type.KEY_GENERATION, new ASN1Encodable[0]); // TODO:
    }

    public MessageReply handle(Message message)
    {
        switch (((ClientMessage)message).getType())
        {
        default:
            System.err.println("unknown command");
        }
        return null;  // TODO:
    }

    public boolean isAbleToHandle(Enum type)
    {
        return type == ClientMessage.Type.GENERATE_KEY_PAIR;
    }
}