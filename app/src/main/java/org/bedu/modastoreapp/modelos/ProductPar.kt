package org.bedu.modastoreapp.modelos

import android.os.Parcel
import android.os.Parcelable

class ProductPar (
    val name: String,
    val description: String,
    val price: String,
    val rating: Float,
    val idImage: Int
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readFloat(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(price)
        parcel.writeFloat(rating)
        parcel.writeInt(idImage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductPar> {
        override fun createFromParcel(parcel: Parcel): ProductPar {
            return ProductPar(parcel)
        }

        override fun newArray(size: Int): Array<ProductPar?> {
            return arrayOfNulls(size)
        }
    }
}
