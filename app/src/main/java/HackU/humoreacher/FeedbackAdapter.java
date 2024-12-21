package HackU.humoreacher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import HackU.humoreacher.entities.Evaluation;

public class FeedbackAdapter extends BaseAdapter {

    private final Context context;
    private final List<Evaluation> evaluations;

    public FeedbackAdapter(Context context, List<Evaluation> evaluations) {
        this.context = context;
        this.evaluations = evaluations;
    }

    @Override
    public int getCount() {
        return evaluations.size();
    }

    @Override
    public Object getItem(int position) {
        return evaluations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        // ViewHolder パターンの使用でパフォーマンス向上
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_feedback, parent, false);
            holder = new ViewHolder();
            holder.feedbackText = convertView.findViewById(R.id.feedbackText);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // データをバインド
        Evaluation evaluation = evaluations.get(position);
        holder.feedbackText.setText(evaluation.getFeedback());

        return convertView;
    }

    // ViewHolder クラス
    private static class ViewHolder {
        TextView feedbackText;
    }
}
