/*
 * Copyright (C) 2015 Square, Inc.
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
package com.squareup.haha.perflib;

import androidx.annotation.NonNull;
import java.util.HashSet;
import java.util.Set;

public final class HahaSpy {

  public static @NonNull Instance allocatingThread(@NonNull Instance instance) {
    Snapshot snapshot = instance.mHeap.mSnapshot;
    int threadSerialNumber;
    if (instance instanceof RootObj) {
      threadSerialNumber = ((RootObj) instance).mThread;
    } else {
      threadSerialNumber = instance.mStack.mThreadSerialNumber;
    }
    ThreadObj thread = snapshot.getThread(threadSerialNumber);
    return snapshot.findInstance(thread.mId);
  }

  /**
   * Returns the GC Roots for all heaps in the Snapshot. Unfortunately,
   * {@link Snapshot#getGCRoots()} only returns the GC Roots of the first heap.
   */
  public static Set<RootObj> allGcRoots(Snapshot snapshot) {
    Set<RootObj> allRoots = new HashSet<>();
    for (Heap heap : snapshot.getHeaps()) {
      allRoots.addAll(heap.mRoots);
    }
    return allRoots;
  }

  private HahaSpy() {
    throw new AssertionError();
  }
}
