package com.enderio.conduits.common;

import net.minecraft.core.Vec3i;

import java.util.Arrays;

public class Area {
    private final Vec3i min;
    private final Vec3i max;

    public Area(Vec3i... targets) {
        this(new Vec3i(
                Arrays.stream(targets).mapToInt(Vec3i::getX).min().getAsInt(),
                Arrays.stream(targets).mapToInt(Vec3i::getY).min().getAsInt(),
                Arrays.stream(targets).mapToInt(Vec3i::getZ).min().getAsInt()
            ),
            new Vec3i(
                Arrays.stream(targets).mapToInt(Vec3i::getX).max().getAsInt(),
                Arrays.stream(targets).mapToInt(Vec3i::getY).max().getAsInt(),
                Arrays.stream(targets).mapToInt(Vec3i::getZ).max().getAsInt()
            )
        );
    }

    private Area(Vec3i min, Vec3i max) {
        this.min = min;
        this.max = max;
    }

    public void makeContain(Vec3i vector) {
        min.setX(Math.min(min.getX(), vector.getX()));
        min.setY(Math.min(min.getY(), vector.getY()));
        min.setZ(Math.min(min.getZ(), vector.getZ()));
        max.setX(Math.max(max.getX(), vector.getX()));
        max.setY(Math.max(max.getY(), vector.getY()));
        max.setZ(Math.max(max.getZ(), vector.getZ()));
    }

    public Vec3i getMin() {
        return min;
    }

    public Vec3i getMax() {
        return max;
    }

    public Vec3i size() {
        return new Vec3i(
            max.getX() - min.getX() + 1,
            max.getY() - min.getY() + 1,
            max.getZ() - min.getZ() + 1
        );
    }

    public boolean contains(Vec3i vector) {
        if (min.getX() > vector.getX()  || vector.getX() > max.getX()) {
            return false;
        }

        if (min.getY() > vector.getY()  || vector.getY() > max.getY()) {
            return false;
        }

        return min.getZ() <= vector.getZ() && vector.getZ() <= max.getZ();
    }
}
