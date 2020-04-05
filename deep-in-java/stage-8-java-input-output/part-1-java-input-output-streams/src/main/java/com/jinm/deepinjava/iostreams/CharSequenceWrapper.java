package com.jinm.deepinjava.iostreams;

import java.util.stream.IntStream;

/**
 * 装饰器模式/包装模式
 *
 */
public class CharSequenceWrapper implements CharSequence {

    private final CharSequence delegate;

    public CharSequenceWrapper(CharSequence delegate) {
        this.delegate = delegate;
    }

    @Override
    public int length() {
        return delegate.length();
    }

    @Override
    public char charAt(int index) {
        return delegate.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return delegate.subSequence(start, end);
    }

    @Override
    public String toString() {
        return delegate.toString();
    }

    @Override
    public IntStream chars() {
        return delegate.chars();
    }

    @Override
    public IntStream codePoints() {
        return delegate.codePoints();
    }

    public String getDescription() {
        return toString();
    }
}
