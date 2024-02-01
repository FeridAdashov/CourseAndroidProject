package techstack.course.androidproject

import android.animation.ValueAnimator
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout

//private val attemptListener: CardGameAttemptsListener


interface CardGameAttemptsListener {
    fun attempt()
    fun finish(status: Status)
}

enum class Status(val title: String) {
    EMPTY(""), ONE("CAT"), TWO("DOG")
}

class TikTakGameGridAdapter(private val activity: Activity) : BaseAdapter() {

    private var isFirstPlayer = true
    private var isFinished = false

    var cards: ArrayList<ArrayList<Status>> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var cardDimension: Int = 100
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val mInflater = activity.layoutInflater

        val myView = mInflater.inflate(R.layout.memory_game_grid_item, null)

        val holder = ViewHolder(myView)

        setViewEvents(holder, position)

        return myView
    }

    private fun setViewEvents(holder: ViewHolder, position: Int) {
        val positionX = position / cards.size
        val positionY = position % cards.size

        holder.parentView.layoutParams = LinearLayout.LayoutParams(cardDimension, cardDimension)

        holder.mainView.setOnClickListener {
            rotateIn(holder.parentView) {
                if (isFirstPlayer) {
                    holder.cardImg.setImageResource(R.drawable.ic_cat)
                    cards[positionX][positionY] = Status.ONE
                } else {
                    holder.cardImg.setImageResource(R.drawable.img_dog)
                    cards[positionX][positionY] = Status.TWO
                }

                checkGameWinner(positionX, positionY)

                isFirstPlayer = !isFirstPlayer
            }

//            attemptListener.attempt()
        }
    }

    private fun checkGameWinner(positionX: Int, positionY: Int) {
        if (checkColumn(positionX, positionY) ||
            checkRow(positionX, positionY) ||
            checkDiagonalOne(positionX, positionY) ||
            checkDiagonalTwo(positionX, positionY)
        ) {
            isFinished = true
//            attemptListener.finish(cards[positionX][positionY])
        }
    }

    private fun checkRow(positionX: Int, positionY: Int): Boolean {
        val status = cards[positionX][positionY]

        var allSame = true
        for (i in 0 until cards.size) {
            if (cards[positionX][i] != status) {
                allSame = false
                break
            }
        }

        return allSame
    }

    private fun checkColumn(positionX: Int, positionY: Int): Boolean {
        val status = cards[positionX][positionY]

        var allSame = true
        for (i in 0 until cards.size) {
            if (cards[i][positionY] != status) {
                allSame = false
                break
            }
        }

        return allSame
    }

    private fun checkDiagonalOne(positionX: Int, positionY: Int): Boolean {
        val status = cards[positionX][positionY]

        var allSame = true
        for (i in 0 until cards.size) {
            if (cards[i][i] != status) {
                allSame = false
                break
            }
        }

        return allSame
    }

    private fun checkDiagonalTwo(positionX: Int, positionY: Int): Boolean {
        val status = cards[positionX][positionY]

        var allSame = true
        for (i in 0 until cards.size) {
            if (cards[cards.size - i - 1][i] != status) {
                allSame = false
                break
            }
        }

        return allSame
    }

    private fun rotateIn(view: View, doIt: () -> Unit) {
        val animator = ValueAnimator.ofFloat(0.0f, 1.0f)
        animator.duration = 200
        animator.addUpdateListener { animation ->
            run {
                val value = animation.animatedValue as Float
                view.rotationY = value * 90

                if (value >= 1.0) {
                    doIt()
                    rotateOut(view)
                }
            }
        }
        animator.start()
    }

    private fun rotateOut(view: View) {
        val animator = ValueAnimator.ofFloat(1.0f, 0.0f)
        animator.duration = 200
        animator.addUpdateListener { animation ->
            run {
                val value = animation.animatedValue as Float
                view.rotationY = value * 90
            }
        }
        animator.start()
    }

    override fun getItem(position: Int): Any {
        return cards[position]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return cards.size * cards.size
    }

    private class ViewHolder(itemView: View) {
        val parentView: FrameLayout = itemView.findViewById(R.id.memoryGameBaseLayout)
        val mainView: RelativeLayout = itemView.findViewById(R.id.memoryCardMainView)
        val cardImg: ImageView = itemView.findViewById(R.id.memoryCardImg)
    }
}