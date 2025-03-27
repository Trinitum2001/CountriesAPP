package com.example.parcial1

import android.os.Parcel
import android.os.Parcelable

data class Country(val name: Name, val region: String, val population: Long, val flags: Flag) : Parcelable {
    data class Name(val common: String, val official: String) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readString()!!
        )

        override fun describeContents(): Int {
            return 0
        }

        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeString(common)
            dest.writeString(official)
        }

        companion object CREATOR : Parcelable.Creator<Name> {
            override fun createFromParcel(parcel: Parcel): Name {
                return Name(parcel)
            }

            override fun newArray(size: Int): Array<Name?> {
                return arrayOfNulls(size)
            }
        }
    }

    data class Flag(val png: String) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString()!!
        )

        override fun describeContents(): Int {
            return 0
        }

        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeString(png)
        }

        companion object CREATOR : Parcelable.Creator<Flag> {
            override fun createFromParcel(parcel: Parcel): Flag {
                return Flag(parcel)
            }

            override fun newArray(size: Int): Array<Flag?> {
                return arrayOfNulls(size)
            }
        }
    }

    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Name::class.java.classLoader)!!,
        parcel.readString()!!,
        parcel.readLong(),
        parcel.readParcelable(Flag::class.java.classLoader)!!
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeParcelable(name, flags)
        dest.writeString(region)
        dest.writeLong(population)
        dest.writeParcelable(this.flags, flags)
    }

    companion object CREATOR : Parcelable.Creator<Country> {
        override fun createFromParcel(parcel: Parcel): Country {
            return Country(parcel)
        }

        override fun newArray(size: Int): Array<Country?> {
            return arrayOfNulls(size)
        }
    }
}