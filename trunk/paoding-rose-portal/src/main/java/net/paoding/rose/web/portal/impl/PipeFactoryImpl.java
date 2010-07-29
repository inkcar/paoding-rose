/*
 * Copyright 2007-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.paoding.rose.web.portal.impl;

import net.paoding.rose.web.Invocation;

/**
 * 
 * @author 王志亮 [qieqie.wang@gmail.com]
 * 
 */

public class PipeFactoryImpl implements PipeFactory {

    @Override
    public Pipe getPipe(Invocation inv, boolean create) {
        Pipe pipe = (Pipe) inv.getRequest().getAttribute("$$paoding-rose.pipe");
        if (pipe == null && create) {
            pipe = new PipeImpl(inv);
            inv.getRequest().setAttribute("$$paoding-rose.pipe", pipe);
        }
        return pipe;
    }

}
